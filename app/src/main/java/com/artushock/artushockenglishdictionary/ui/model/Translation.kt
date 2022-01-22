package com.artushock.artushockenglishdictionary.ui.model

data class Translation(
    val text: String,
    val translation: String,
    val note: String?,
    val imageUrl: String,
    val soundUrl: String,
)