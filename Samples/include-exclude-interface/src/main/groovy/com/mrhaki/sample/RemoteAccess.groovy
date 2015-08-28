package com.mrhaki.spock

class RemoteAccess implements Access {
    @Delegate WordRepository wordRepository = new WordRepository()
}
