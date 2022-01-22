package com.artushock.artushockenglishdictionary.data.repository.remote

import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<RemoteDataModel>>
}