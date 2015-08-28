package com.mrhaki.spock

class LocalWordRepositorySpec 
        extends WordRepositorySpec<LocalAccess> {

    def setup() {
        access = new LocalAccess()
    }

}