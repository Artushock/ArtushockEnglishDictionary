package com.artushock.artushockenglishdictionary.data.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getTranslations(word: String): Observable<T>
}
