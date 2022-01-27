package com.artushock.artushockenglishdictionary.viewModels

import androidx.lifecycle.LiveData
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    private val interactor: ResultInteractor<AppState>
) : BaseViewModel<AppState>() {

    override fun getTranslations(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getTranslation(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { resultLiveData.value = AppState.Loading}
                .subscribeWith(getObserver()))
        return super.getTranslations(word)
    }

    private fun getObserver(): DisposableObserver<AppState>{
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                resultLiveData.value = state
            }

            override fun onError(e: Throwable) {
                resultLiveData.value = AppState.Error(e)
            }

            override fun onComplete() {
                // do nothing
            }
        }
    }
}