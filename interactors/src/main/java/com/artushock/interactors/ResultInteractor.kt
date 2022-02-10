package com.artushock.interactors


interface ResultInteractor<T> {
    suspend fun getTranslation(word: String): T
}