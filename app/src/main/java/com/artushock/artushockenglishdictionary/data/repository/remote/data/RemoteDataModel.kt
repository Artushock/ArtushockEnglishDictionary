package com.artushock.artushockenglishdictionary.data.repository.remote.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteDataModel(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("text")
    val text: String = "",
    @SerializedName("meanings")
    val meanings: List<RemoteMeaning> = listOf(),
) : Parcelable