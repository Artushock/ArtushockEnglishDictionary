package com.artushock.artushockenglishdictionary.ui.model

import com.artushock.artushockenglishdictionary.entities.DataModel

class Mapper {

    fun convertDataListToTranslationList(dataList: List<DataModel>): List<Translation> {

        return arrayListOf<Translation>().apply {
            dataList.forEach{ dataItem ->
                dataItem.meanings!!.forEach { meaning ->
                    this.add(
                        Translation(
                            text = dataItem.text.toString(),
                            translation = meaning.dataTranslation?.text.toString(),
                            note = meaning.dataTranslation?.note,
                            imageUrl = meaning.imageUrl.toString(),
                            soundUrl = meaning.soundUrl.toString(),
                        )
                    )
                }
            }
        }
    }
}