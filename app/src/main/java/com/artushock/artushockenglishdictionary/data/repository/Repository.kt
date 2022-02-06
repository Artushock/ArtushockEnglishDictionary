package com.artushock.artushockenglishdictionary.data.repository

interface Repository<T, K> {

    suspend fun getTranslations(word: String): T

    suspend fun getHistoryData(): K

}
