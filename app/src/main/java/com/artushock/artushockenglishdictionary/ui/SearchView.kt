package com.artushock.artushockenglishdictionary.ui

interface SearchView : IBaseView {
    fun sendRequest(word: String)
}