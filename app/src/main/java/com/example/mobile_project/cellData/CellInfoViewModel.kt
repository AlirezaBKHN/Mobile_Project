package com.example.mobile_project.cellData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobile_project.AppDatabase
import kotlinx.coroutines.launch

class CellInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CellInfoRepository
    private val _allCellInfo = MutableLiveData<List<CellInfoEntity>>()

    // LiveData that the UI can observe
    val allCellInfo: LiveData<List<CellInfoEntity>> get() = _allCellInfo

    init {
        val cellInfoDao = AppDatabase.getDatabase(application).cellInfoDao()
        repository = CellInfoRepository(cellInfoDao)
        loadAllCellInfo()
    }

    // Function to insert cell info
    fun insert(cellInfo: CellInfoEntity) = viewModelScope.launch {
        repository.insert(cellInfo)
        loadAllCellInfo()  // Refresh the data after insertion
    }

    // Function to load all cell info
    private fun loadAllCellInfo() = viewModelScope.launch {
        _allCellInfo.value = repository.getAllCellInfo()
    }
}
