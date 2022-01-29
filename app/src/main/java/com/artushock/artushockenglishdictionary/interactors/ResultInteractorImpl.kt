package com.artushock.artushockenglishdictionary.interactors

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.entities.DataModel
import io.reactivex.Observable

class ResultInteractorImpl(
    val repository: Repository<List<DataModel>>
) : ResultInteractor<AppState>{

    override fun getTranslation(word: String): Observable<AppState> {
        return repository.getTranslations(word)
            .map { AppState.Success(it) }
    }
}