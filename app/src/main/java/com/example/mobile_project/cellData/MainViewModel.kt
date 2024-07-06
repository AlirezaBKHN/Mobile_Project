package com.example.mobile_project.cellData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val cellInfoRepository: MainRepository
) : ViewModel() {

    fun insertGsmCell(gsmCell: GsmCellEntity) {
        viewModelScope.launch {
            cellInfoRepository.insertGsmCell(gsmCell)
        }
    }

    suspend fun getAllGsmCells(): List<GsmCellEntity> {
        return cellInfoRepository.getAllGsmCell()
    }

    fun insertUmtsCell(umtsCell: UmtsCellEntity) {
        viewModelScope.launch {
            cellInfoRepository.insertUmtsCell(umtsCell)
        }
    }

    suspend fun getAllUmtsCells(): List<UmtsCellEntity> {
        return cellInfoRepository.getAllUmtsCell()
    }

    fun insertLteCell(lteCell: LteCellEntity) {
        viewModelScope.launch {
            cellInfoRepository.insertLteCell(lteCell)
        }
    }

    suspend fun getAllLteCells(): List<LteCellEntity> {
        return cellInfoRepository.getAllLteCell()
    }
}
