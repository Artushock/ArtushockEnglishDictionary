package com.artushock.artushockenglishdictionary.interactors

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity
import com.artushock.artushockenglishdictionary.entities.DataModel

class HistoryInteractorImpl(
    val repository: Repository<List<DataModel>, List<HistoryEntity>>,
): HistoryInteractor<List<HistoryEntity>> {

    override suspend fun getHistoryData(): List<HistoryEntity> {
        return repository.getHistoryData()
    }
}