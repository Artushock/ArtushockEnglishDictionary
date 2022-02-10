package com.artushock.repository.repository.remote.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.artushock.repository.repository.local.LocalRepository
import com.artushock.repository.repository.local.room.HistoryDao
import com.artushock.repository.repository.local.room.HistoryEntity
import java.text.SimpleDateFormat
import java.util.*

class RoomImpl(
    private val historyDao: HistoryDao,
) : LocalRepository<List<HistoryEntity>> {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun saveWordToDB(word: String, status: Boolean) {
        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        historyDao.insert(HistoryEntity(word, currentDate, status))
    }

    override suspend fun getAll(): List<HistoryEntity> {
        return historyDao.all()
    }
}