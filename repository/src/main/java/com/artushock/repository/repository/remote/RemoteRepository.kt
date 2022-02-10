package com.artushock.repository.repository.remote

interface RemoteRepository<T> {
    suspend fun getTranslations(word: String): T
}
