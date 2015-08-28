import com.mrhaki.spock.RemoteSpec

runner {
    println "Using RemoteSpockConfig"

    // Include only test classes that
    // implement the RemoteSpec interface.
    include RemoteSpec

    // Alternative syntax
    // to only look for classes or interfaces.
    // include {
    //     baseClass RemoteSpec
    // }

    // We can also add a condition in
    // the configuration file.
    // In this case we check for a Java
    // system property and if set the
    // specs with interface RemoteSpec
    // are not run.
    if (System.properties['spock.ignore.Remote']) {
        exclude RemoteSpec
    }
}