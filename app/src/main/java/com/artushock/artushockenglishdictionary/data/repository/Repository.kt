package com.artushock.artushockenglishdictionary.data.repository

interface Repository<T> {
    suspend fun getTranslations(word: String): T
}
