package com.example.mobile_project.cellData

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CellInfoRepository(private val cellInfoDao: CellInfoDao) {

    // Room executes all queries on a separate thread
    @WorkerThread
    suspend fun insert(cellInfo: CellInfoEntity) {
        withContext(Dispatchers.IO) {
            cellInfoDao.insert(cellInfo)
        }
    }

    // Get all cell info
    suspend fun getAllCellInfo(): List<CellInfoEntity> {
        return withContext(Dispatchers.IO) {
            cellInfoDao.getAllCellInfo()
        }
    }
}


