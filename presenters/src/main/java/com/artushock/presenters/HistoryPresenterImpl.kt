package com.artushock.presenters

import com.artushock.artushockenglishdictionary.interactors.HistoryInteractor
import com.artushock.artushockenglishdictionary.ui.HistoryView
import com.artushock.repository.repository.local.room.HistoryEntity
import kotlinx.coroutines.*

class HistoryPresenterImpl(
    private val interactor: HistoryInteractor<List<HistoryEntity>>,
) : HistoryPresenter<HistoryView> {

    private var currentView: HistoryView? = null

    private val historyPresenterCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(throwable: Throwable) {
        currentView?.showError(throwable.message.toString())
    }


    override fun showHistory() {
        currentView?.showProgress()
        cancelJob()

        historyPresenterCoroutineScope.launch {
            currentView?.showResult(startInteractor())
        }
    }

    private suspend fun startInteractor(): List<HistoryEntity> {
        return withContext(Dispatchers.IO) {
            interactor.getHistoryData()
        }
    }

    override fun attachView(view: HistoryView) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: HistoryView) {
        cancelJob()
        if (currentView == view) {
            currentView = null
        }
    }

    private fun cancelJob() {
        historyPresenterCoroutineScope.coroutineContext.cancelChildren()
    }
}