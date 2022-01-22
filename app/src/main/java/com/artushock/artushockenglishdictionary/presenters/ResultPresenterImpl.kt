package com.artushock.artushockenglishdictionary.presenters

import com.artushock.artushockenglishdictionary.data.repository.RepositoryImpl
import com.artushock.artushockenglishdictionary.data.repository.local.RoomImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.RetrofitImpl
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import com.artushock.artushockenglishdictionary.ui.ResultView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ResultPresenterImpl(
    private val interactor: ResultInteractor<AppState> = ResultInteractorImpl(
        repository = RepositoryImpl(
            localRepository = RoomImpl(),
            remoteRepository = RetrofitImpl()
        )
    ),

    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProviderImpl(),
) : ResultPresenter<ResultView> {

    private var currentView: ResultView? = null

    override fun getTranslations(word: String) {
        compositeDisposable.add(
            interactor.getTranslation(word)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { currentView?.showProgress() }
            .subscribeWith(getObserver()))
    }

    private fun getObserver(): DisposableObserver<AppState> {

        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.showResult(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.showError(e.message.toString())
            }

            override fun onComplete() {
                //TODO("make sound")
            }
        }
    }

    override fun attachView(view: ResultView) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: ResultView) {
        compositeDisposable.clear()
        if (currentView == view) {
            currentView = null
        }
    }
}