package com.artushock.repository.repository.remote

import com.artushock.repository.repository.remote.data.RemoteDataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<RemoteDataModel>>
}