package com.artushock.models.entities

data class DataModel(
    val id: Int? = -1,
    val text: String? = "",
    val meanings: List<Meaning>? = listOf(Meaning()),
)

data class Meaning(
    val id: Int? = -1,
    val partOfSpeechCode: String? = "",
    val text: String? = "",
    val dataTranslation: DataTranslation? = DataTranslation(),
    val imageUrl: String? = "",
    val transcription: String? = "",
    val soundUrl: String? = "",
)

data class DataTranslation (
    val text: String? = "",
    val note: String? = "",
)