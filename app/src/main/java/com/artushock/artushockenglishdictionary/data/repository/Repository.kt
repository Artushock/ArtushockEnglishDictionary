package com.artushock.artushockenglishdictionary.data.repository

import com.artushock.artushockenglishdictionary.entities.DataModel
import io.reactivex.Observable

interface Repository {
    fun getTranslations(word: String) : Observable<List<DataModel>>
}
