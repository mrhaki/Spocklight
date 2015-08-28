package com.mrhaki.spock


class LocalAccess implements Access {
    @Delegate WordRepository wordRepository = new WordRepository()
}
