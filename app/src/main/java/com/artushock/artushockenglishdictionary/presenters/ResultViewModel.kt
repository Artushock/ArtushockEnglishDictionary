package com.artushock.artushockenglishdictionary.presenters

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import com.artushock.artushockenglishdictionary.data.repository.RepositoryImpl
import com.artushock.artushockenglishdictionary.data.repository.local.RoomImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.RetrofitImpl
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import com.artushock.artushockenglishdictionary.ui.MainActivity
import io.reactivex.observers.DisposableObserver

class ResultViewModel(
    private val interactor: ResultInteractor<AppState> = ResultInteractorImpl(
        repository = RepositoryImpl(
            localRepository = RoomImpl(),
            remoteRepository = RetrofitImpl()
        )
    )
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