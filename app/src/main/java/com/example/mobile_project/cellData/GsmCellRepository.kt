package com.example.mobile_project.cellData

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GsmCellRepository(private val gsmCellDao: GsmCellDao) {

    @WorkerThread
    suspend fun insert(gsmCell: GsmCellEntity) {
        withContext(Dispatchers.IO) {
            gsmCellDao.insert(gsmCell)
        }
    }

    suspend fun getAllGsmCell(): List<GsmCellEntity> {
        return withContext(Dispatchers.IO) {
            gsmCellDao.getAllGsmCell()
        }
    }
}
