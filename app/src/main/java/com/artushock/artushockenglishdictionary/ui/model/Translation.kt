package com.artushock.artushockenglishdictionary.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    val text: String,
    val translation: String,
    val note: String?,
    val imageUrl: String,
    val soundUrl: String,
) : Parcelable