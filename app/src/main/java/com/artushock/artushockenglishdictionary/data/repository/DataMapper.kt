package com.artushock.artushockenglishdictionary.data.repository

import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteMeaning
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteTranslation
import com.artushock.artushockenglishdictionary.entities.DataModel
import com.artushock.artushockenglishdictionary.entities.DataTranslation
import com.artushock.artushockenglishdictionary.entities.Meaning

class DataMapper {

    fun convertRemoteListToDataModelList(remoteDataList: List<RemoteDataModel>): List<DataModel> {
        return arrayListOf<DataModel>().apply {
            remoteDataList.forEach {
                this.add(convertRemoteModelToDataModel(it))
            }
        }
    }

    private fun convertRemoteModelToDataModel(remoteModel: RemoteDataModel): DataModel {

        val convertedMeanings = arrayListOf<Meaning>().apply {
            remoteModel.meanings.forEach {
                this.add(convertRemoteMeaningToMeaning(it))
            }
        }

        return DataModel(
            id = remoteModel.id,
            text = remoteModel.text,
            meanings = convertedMeanings
        )
    }

    private fun convertRemoteMeaningToMeaning(remoteMeaning: RemoteMeaning): Meaning {

        return Meaning(
            id = remoteMeaning.id,
            partOfSpeechCode = remoteMeaning.partOfSpeechCode,
            text = remoteMeaning.text,
            dataTranslation = convertRemoteTranslationToTranslation(remoteMeaning.translation),
            imageUrl = remoteMeaning.imageUrl,
            transcription = remoteMeaning.transcription,
            soundUrl = remoteMeaning.soundUrl
        )
    }

    private fun convertRemoteTranslationToTranslation(remoteTranslation: RemoteTranslation?): DataTranslation {
        return if (remoteTranslation == null) {
            DataTranslation()
        } else {
            DataTranslation(
                remoteTranslation.text,
                remoteTranslation.note
            )
        }
    }
}