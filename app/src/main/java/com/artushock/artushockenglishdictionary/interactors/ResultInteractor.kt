package com.artushock.artushockenglishdictionary.interactors

import io.reactivex.Observable


interface ResultInteractor<T>{
    fun getTranslation(word: String): Observable<T>
}