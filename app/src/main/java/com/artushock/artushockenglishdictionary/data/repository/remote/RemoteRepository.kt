package com.artushock.artushockenglishdictionary.data.repository.remote

interface RemoteRepository<T> {
    suspend fun getTranslations(word: String): T
}
