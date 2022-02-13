package com.artushock.artushockenglishdictionary.data.repository.local

interface LocalRepository <T> {

    suspend fun saveWordToDB(word: String, status: Boolean)

    suspend fun getAll(): T
}
