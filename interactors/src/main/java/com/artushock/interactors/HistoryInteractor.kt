package com.artushock.interactors

interface HistoryInteractor<T> {
    suspend fun getHistoryData(): T
}