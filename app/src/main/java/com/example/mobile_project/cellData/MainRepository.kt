package com.example.mobile_project.cellData

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val cellInfoDao: CellInfoDao,
    private val gsmCellDao: GsmCellDao,
    private val umtsCellDao: UmtsCellDao,
    private val lteCellDao: LteCellDao
) {

    private val gsmCellRepository = GsmCellRepository(gsmCellDao)
    private val umtsCellRepository = UmtsCellRepository(umtsCellDao)
    private val lteCellRepository = LteCellRepository(lteCellDao)

    // Room executes all queries on a separate thread
    suspend fun insert(cellInfo: CellInfoEntity) {
        withContext(Dispatchers.IO) {
            cellInfoDao.insert(cellInfo)
        }
    }

    suspend fun getAllCellInfo(): List<CellInfoEntity> {
        return withContext(Dispatchers.IO) {
            cellInfoDao.getAllCellInfo()
        }
    }

    suspend fun insertGsmCell(gsmCell: GsmCellEntity) {
        gsmCellRepository.insert(gsmCell)
    }

    suspend fun getAllGsmCell(): List<GsmCellEntity> {
        return gsmCellRepository.getAllGsmCell()
    }

    suspend fun insertUmtsCell(umtsCell: UmtsCellEntity) {
        umtsCellRepository.insert(umtsCell)
    }

    suspend fun getAllUmtsCell(): List<UmtsCellEntity> {
        return umtsCellRepository.getAllUmtsCell()
    }

    suspend fun insertLteCell(lteCell: LteCellEntity) {
        lteCellRepository.insert(lteCell)
    }

    suspend fun getAllLteCell(): List<LteCellEntity> {
        return lteCellRepository.getAllLteCell()
    }
}
