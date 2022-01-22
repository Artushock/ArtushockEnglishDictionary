package com.artushock.artushockenglishdictionary.data.repository.remote

import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import io.reactivex.Observable

interface RemoteRepository {
    fun getTranslations(word: String): Observable<List<RemoteDataModel>>
}
