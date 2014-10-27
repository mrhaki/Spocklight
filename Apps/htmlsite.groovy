import groovy.xml.*
import groovy.text.*

baseDir = '../OfflineSite'

tocList = []

def bloggerId = '6671019398434141469'
label = 'Spocklight'
def baseURI = "https://www.blogger.com/feeds/$bloggerId/posts/default/-/$label" 

def nextLink = baseURI 

while (nextLink) {
    println "Getting posts from $nextLink"
    feed = new XmlSlurper().parse(nextLink)
    feed.entry.each { 
        handleEntry(it) 
    }
    nextLink = feed.link.find { it.@rel == 'next' }.@href.toString() - "/-/${label}"
}

def templateEngine = new GStringTemplateEngine()
def template = templateEngine.createTemplate(new File('toc-template.gtpl')).make([toc: tocList])
def tocFile = new File(baseDir, 'index.html')
tocFile.withWriter {
    it.write template
}

def ant = new AntBuilder()
ant.zip basedir: new File(baseDir), destfile: new File("../${label}-Site.zip")

def handleEntry(entry) {
    /*
    def dir = createDirectory(entry.title)
    handleText entry.content, dir
    handleInfo entry, dir
    */
    //println XmlUtil.serialize(new StreamingMarkupBuilder().bindNode(entry))
    def templateEngine = new GStringTemplateEngine()
    def content = entry.content.toString()
    content = content[0..(content.indexOf('<div class="blogger-post-footer"') - 1)]
    def template = templateEngine.createTemplate(new File('post-template.gtpl')).make([content: content, title: entry.title])
    def filename = entry.link.find { it.@title && it.@rel == 'alternate' }.@href.toString()
    filename = filename[(filename.lastIndexOf("/") + 1)..-1]
    
    tocList << [link: filename, title: entry.title]
    
    def htmlFile = new File(baseDir, filename)
  	htmlFile.withWriter { 
  	    println "Writing contents to $htmlFile.absolutePath"
  		it.write template 
  	}
}

def handleInfo(entry, dir) {
    def link = entry.link.find { it.@rel == 'alternate' }.@href
    def infoFile = new File(dir, 'info.groovy')
    infoFile.delete()
    if (!infoFile.exists()) {
        infoFile << "url=$link" 
        infoFile << "\n"
        infoFile << "title=$entry.title"
    }
}

def handleText(content, dir) {
    def pres = content.toString() =~ /(?ms)<pre class="brush:groovy.*">(.*?)<\/pre>/
    def sample = new File(dir, 'sample.groovy')
    if (!sample.exists()) {
        println "Saving sample code in $sample."
        pres.each {
            def first = true
            it[1].eachLine { line -> 
                if (!(first && !line)) {
                    sample << line + '\n'
                }
                first = false
            }            
        }        
    }    
}

def createDirectory(title) {    
    def newTitle = title.toString() - "${label}: "
    newTitle = newTitle.trim()
    newTitle = newTitle.replaceAll(/(,|'|"|\?|:|\/)/, '')
    newTitle = newTitle.replaceAll(/(\.)/, ' ')
    newTitle = newTitle - '('
    newTitle = newTitle - ')'
    if (newTitle.startsWith("the")) {
        newTitle = newTitle[0].toUpperCase() + newTitle[1..-1]
    }
    
    if (newTitle == 'String.multiply()') { 
        newTitle = 'String multiply'
    }
    
    newTitle = newTitle.replaceAll(/\s+/, '_')
    def dir = new File(baseDir, newTitle)
    if (!dir.exists()) {
        println "Creating dir $dir.absolutePath" 
        dir.mkdirs()
    }
    dir
}
