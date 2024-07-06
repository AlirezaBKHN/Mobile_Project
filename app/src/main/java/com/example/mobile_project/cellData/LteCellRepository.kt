package com.example.mobile_project.cellData

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LteCellRepository(private val lteCellDao: LteCellDao) {

    @WorkerThread
    suspend fun insert(lteCell: LteCellEntity) {
        withContext(Dispatchers.IO) {
            lteCellDao.insert(lteCell)
        }
    }

    suspend fun getAllLteCell(): List<LteCellEntity> {
        return withContext(Dispatchers.IO) {
            lteCellDao.getAllLteCell()
        }
    }
}
