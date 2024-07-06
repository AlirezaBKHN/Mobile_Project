package com.example.mobile_project.cellData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GsmCellViewModel(private val gsmCellRepository: GsmCellRepository) : ViewModel() {

    fun insert(gsmCell: GsmCellEntity) {
        viewModelScope.launch {
            gsmCellRepository.insert(gsmCell)
        }
    }

    suspend fun getAllGsmCells(): List<GsmCellEntity> {
        return gsmCellRepository.getAllGsmCell()
    }
}
