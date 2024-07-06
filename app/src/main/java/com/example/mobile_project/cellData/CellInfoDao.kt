package com.example.mobile_project.cellData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CellInfoDao {
    @Insert
    suspend fun insert(cellInfo: CellInfoEntity)

    @Query("SELECT * FROM cell_info ORDER BY timestamp DESC")
    suspend fun getAllCellInfo(): List<CellInfoEntity>
}





