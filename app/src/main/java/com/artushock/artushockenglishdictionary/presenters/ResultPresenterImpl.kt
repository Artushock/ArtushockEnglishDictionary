package com.artushock.artushockenglishdictionary.presenters

import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.ui.ResultView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ResultPresenterImpl(
    private val interactor: ResultInteractor<AppState>,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
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