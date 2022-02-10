package com.artushock.artushockenglishdictionary.ui

import com.artushock.repository.repository.local.room.HistoryEntity

interface HistoryView : IBaseView {
    fun showResult(list: List<HistoryEntity>)
    fun showError(errorMessage: String)
    fun showProgress()
}