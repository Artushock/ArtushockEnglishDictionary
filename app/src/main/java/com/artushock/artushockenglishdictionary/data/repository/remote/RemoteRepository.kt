package com.artushock.artushockenglishdictionary.data.repository.remote

import io.reactivex.Observable

interface RemoteRepository<T> {
    fun getTranslations(word: String): Observable<T>
}
