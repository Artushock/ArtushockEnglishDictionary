package com.artushock.artushockenglishdictionary.interactors


interface ResultInteractor<T> {
    suspend fun getTranslation(word: String): T
}