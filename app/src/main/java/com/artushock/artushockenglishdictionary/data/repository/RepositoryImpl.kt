package com.artushock.artushockenglishdictionary.data.repository

import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.entities.DataModel

class RepositoryImpl(
    private val localRepository: LocalRepository<List<RemoteDataModel>>,
    private val remoteRepository: RemoteRepository<List<RemoteDataModel>>,
) : Repository<List<DataModel>> {

    override suspend fun getTranslations(word: String): List<DataModel> {
        if (isInternetConnected()) {
            val list = remoteRepository.getTranslations(word)
            return DataMapper().convertRemoteListToDataModelList(list)
        } else {
            TODO("Not yet implemented")
        }
    }

    private fun isInternetConnected(): Boolean {
        return true
        TODO("Not yet implemented")
    }
}