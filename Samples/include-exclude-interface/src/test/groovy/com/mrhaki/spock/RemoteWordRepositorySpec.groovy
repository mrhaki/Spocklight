package com.mrhaki.spock

class RemoteWordRepositorySpec 
        extends WordRepositorySpec<RemoteAccess>
        // Implement RemoteSpec marker interface
        implements RemoteSpec {

    def setup() {
        access = new RemoteAccess()
    }

}