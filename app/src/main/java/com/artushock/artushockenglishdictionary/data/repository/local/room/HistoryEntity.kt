package com.artushock.artushockenglishdictionary.data.repository.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    var word: String,

    @field:ColumnInfo(name = "date")
    var date: String?,

    @field:ColumnInfo(name = "status")
    var status: Boolean = false,
)