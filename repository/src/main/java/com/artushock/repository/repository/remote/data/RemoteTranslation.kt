package com.artushock.repository.repository.remote.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteTranslation(
    @SerializedName("text")
    val text: String? = "",
    @SerializedName("note")
    val note: String? = "",
) : Parcelable