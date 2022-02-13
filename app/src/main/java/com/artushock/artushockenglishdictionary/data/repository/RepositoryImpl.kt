package com.artushock.artushockenglishdictionary.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.artushock.artushockenglishdictionary.DictionaryApplication
import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.entities.DataModel

class RepositoryImpl(
    private val localRepository: LocalRepository<List<HistoryEntity>>,
    private val remoteRepository: RemoteRepository<List<RemoteDataModel>>,
) : Repository<List<DataModel>, List<HistoryEntity>> {

    override suspend fun getTranslations(word: String): List<DataModel> {

        return if (isInternetConnected(
                DictionaryApplication.getAppContext()
            )
        ) {
            localRepository.saveWordToDB(word, true)

            val list = remoteRepository.getTranslations(word)
            DataMapper().convertRemoteListToDataModelList(list)
        } else {

            localRepository.saveWordToDB(word, false)

            Toast.makeText(DictionaryApplication.getAppContext(),
                "No internet connections",
                Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    private fun isInternetConnected(
        context: Context,
    ): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo?
        netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    override suspend fun getHistoryData(): List<HistoryEntity> {
        return localRepository.getAll()
    }
}