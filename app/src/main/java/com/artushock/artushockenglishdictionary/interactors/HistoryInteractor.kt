package com.artushock.artushockenglishdictionary.interactors

interface HistoryInteractor<T> {
    suspend fun getHistoryData(): T
}