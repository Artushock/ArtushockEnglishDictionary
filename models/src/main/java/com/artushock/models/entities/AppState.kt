package com.artushock.artushockenglishdictionary.entities

import com.artushock.models.entities.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}