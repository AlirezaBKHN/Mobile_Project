package com.example.mobile_project.cellData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LteCellDao{
    @Insert
    suspend fun insert(lteCell: LteCellEntity)
    @Query("SELECT * FROM LteCell")
    suspend fun getAllLteCell(): List<LteCellEntity>
}