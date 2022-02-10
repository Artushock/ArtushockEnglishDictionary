package com.artushock.repository.repository.remote.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteMeaning(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String = "",
    @SerializedName("text")
    val text: String = "",
    @SerializedName("translation")
    val translation: RemoteTranslation? = RemoteTranslation(),
    @SerializedName("imageUrl")
    val imageUrl: String = "",
    @SerializedName("transcription")
    val transcription: String = "",
    @SerializedName("soundUrl")
    val soundUrl: String = "",
) : Parcelable