package com.artushock.artushockenglishdictionary.ui

import com.artushock.artushockenglishdictionary.entities.AppState

interface ResultView: IBaseView {
    fun showResult(appState: AppState)
    fun showError(errorMessage: String)
    fun showProgress()
}