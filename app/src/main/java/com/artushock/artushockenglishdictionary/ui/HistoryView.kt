package com.artushock.artushockenglishdictionary.ui

import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity

interface HistoryView : IBaseView {
    fun showResult(list: List<HistoryEntity>)
    fun showError(errorMessage: String)
    fun showProgress()
}