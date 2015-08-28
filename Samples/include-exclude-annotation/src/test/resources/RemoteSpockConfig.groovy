import com.mrhaki.spock.Remote

runner {
    // This is Groovy script and we 
    // add arbitrary code.
    println "Using RemoteSpockConfig"

    // Include only test classes or test
    // methods with the @Remote annotation
    include Remote

    // Alternative syntax
    // to only look for annotations.
    // include {
    //     annotation Remote
    // }

    
    // We can also add a condition in
    // the configuration file.
    // In this case we check for a Java
    // system property and if set the
    // specs with @Remote are not run.
    if (System.properties['spock.ignore.Remote']) {
        exclude Remote
    }
}