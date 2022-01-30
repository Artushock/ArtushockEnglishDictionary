package com.artushock.artushockenglishdictionary.interactors

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.entities.DataModel

class ResultInteractorImpl(
    val repository: Repository<List<DataModel>>,
) : ResultInteractor<AppState> {

    override suspend fun getTranslation(word: String): AppState {
        return AppState.Success(repository.getTranslations(word))
    }
}