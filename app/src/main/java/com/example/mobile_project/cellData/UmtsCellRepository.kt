package com.example.mobile_project.cellData

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UmtsCellRepository(private val umtsCellDao: UmtsCellDao) {

    @WorkerThread
    suspend fun insert(umtsCell: UmtsCellEntity) {
        withContext(Dispatchers.IO) {
            umtsCellDao.insert(umtsCell)
        }
    }

    suspend fun getAllUmtsCell(): List<UmtsCellEntity> {
        return withContext(Dispatchers.IO) {
            umtsCellDao.getAllUmtsCell()
        }
    }
}
