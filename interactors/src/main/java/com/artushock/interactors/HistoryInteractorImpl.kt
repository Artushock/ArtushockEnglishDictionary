package com.artushock.interactors

import com.artushock.models.entities.DataModel
import com.artushock.repository.repository.Repository
import com.artushock.repository.repository.local.room.HistoryEntity

class HistoryInteractorImpl(
    val repository: Repository<List<DataModel>, List<HistoryEntity>>,
) : HistoryInteractor<List<HistoryEntity>> {

    override suspend fun getHistoryData(): List<HistoryEntity> {
        return repository.getHistoryData()
    }
}