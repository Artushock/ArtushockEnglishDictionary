package com.artushock.repository.repository

import com.artushock.repository.repository.local.LocalRepository
import com.artushock.repository.repository.local.room.HistoryEntity
import com.artushock.repository.repository.remote.RemoteRepository
import com.artushock.repository.repository.remote.data.RemoteDataModel
import com.artushock.models.entities.DataModel
import kotlin.random.Random

class RepositoryImpl(
    private val localRepository: LocalRepository<List<HistoryEntity>>,
    private val remoteRepository: RemoteRepository<List<RemoteDataModel>>,
) : Repository<List<DataModel>, List<HistoryEntity>> {

    override suspend fun getTranslations(word: String): List<DataModel> {

        return if (isInternetConnected()) {
            localRepository.saveWordToDB(word, true)

            val list = remoteRepository.getTranslations(word)
            DataMapper().convertRemoteListToDataModelList(list)
        } else {

            localRepository.saveWordToDB(word, false)

//            Toast.makeText(DictionaryApplication.getContext(),
//                "No internet connections",
//                Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    private fun isInternetConnected(): Boolean {
        //imitation different internet connection states
        return Random.nextBoolean()
    }

    override suspend fun getHistoryData(): List<HistoryEntity> {
        return localRepository.getAll()
    }
}