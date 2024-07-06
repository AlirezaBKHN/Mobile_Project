package com.example.mobile_project.cellData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LteCellViewModel(private val lteCellRepository: LteCellRepository) : ViewModel() {

    fun insert(lteCell: LteCellEntity) {
        viewModelScope.launch {
            lteCellRepository.insert(lteCell)
        }
    }

    suspend fun getAllLteCells(): List<LteCellEntity> {
        return lteCellRepository.getAllLteCell()
    }
}
