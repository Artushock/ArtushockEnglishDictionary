package com.artushock.interactors

import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.models.entities.DataModel
import com.artushock.repository.repository.Repository
import com.artushock.repository.repository.local.room.HistoryEntity

class ResultInteractorImpl(
    val repository: Repository<List<DataModel>, List<HistoryEntity>>,
) : ResultInteractor<AppState> {

    override suspend fun getTranslation(word: String): AppState {
        return AppState.Success(repository.getTranslations(word))
    }
}