package com.example.mobile_project.cellData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UmtsCellViewModel(private val umtsCellRepository: UmtsCellRepository) : ViewModel() {

    fun insert(umtsCell: UmtsCellEntity) {
        viewModelScope.launch {
            umtsCellRepository.insert(umtsCell)
        }
    }

    suspend fun getAllUmtsCells(): List<UmtsCellEntity> {
        return umtsCellRepository.getAllUmtsCell()
    }
}
