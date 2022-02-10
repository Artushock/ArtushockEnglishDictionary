package com.artushock.repository.repository

interface Repository<T, K> {

    suspend fun getTranslations(word: String): T

    suspend fun getHistoryData(): K

}
