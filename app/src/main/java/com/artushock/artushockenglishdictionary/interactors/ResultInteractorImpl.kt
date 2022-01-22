package com.artushock.artushockenglishdictionary.interactors

import android.util.Log
import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.entities.AppState
import io.reactivex.Observable

class ResultInteractorImpl(
    val repository: Repository
) : ResultInteractor<AppState>{

    override fun getTranslation(word: String): Observable<AppState> {
        return repository.getTranslations(word)
            .map { AppState.Success(it) }
    }
}