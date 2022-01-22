package com.artushock.artushockenglishdictionary.data.repository

import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.entities.DataModel
import io.reactivex.Observable

class RepositoryImpl(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
) : Repository {

    override fun getTranslations(word: String): Observable<List<DataModel>> {
        if (isInternetConnected()) {
          return remoteRepository.getTranslations(word)
              .map {
                DataMapper().convertRemoteListToDataModelList(it)
            }
        } else {
            TODO("Not yet implemented")
        }
    }

    private fun isInternetConnected(): Boolean {
        return true
        TODO("Not yet implemented")
    }
}