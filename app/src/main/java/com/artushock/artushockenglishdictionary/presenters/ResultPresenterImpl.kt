package com.artushock.artushockenglishdictionary.presenters

import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.ui.ResultView
import kotlinx.coroutines.*

class ResultPresenterImpl(
    private val interactor: ResultInteractor<AppState>,
) : ResultPresenter<ResultView> {

    private var currentView: ResultView? = null

    private val resultPresenterCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(throwable: Throwable) {
        currentView?.showError(throwable.message.toString())
    }

    override fun getTranslations(word: String) {
        currentView?.showProgress()
        cancelJob()

        resultPresenterCoroutineScope.launch {
            currentView?.showResult(startInteractor(word))
        }
    }

    private suspend fun startInteractor(word: String): AppState {
        return withContext(Dispatchers.IO) {
            interactor.getTranslation(word)
        }
    }

    override fun attachView(view: ResultView) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: ResultView) {
        cancelJob()
        if (currentView == view) {
            currentView = null
        }
    }

    private fun cancelJob() {
        resultPresenterCoroutineScope.coroutineContext.cancelChildren()
    }
}