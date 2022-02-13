package com.artushock.artushockenglishdictionary.presenters

import com.artushock.artushockenglishdictionary.ui.IBaseView

interface HistoryPresenter<V : IBaseView> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun showHistory()
}
