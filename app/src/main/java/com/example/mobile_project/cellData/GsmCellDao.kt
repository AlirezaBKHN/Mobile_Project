package com.example.mobile_project.cellData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface GsmCellDao{
    @Insert
    suspend fun insert(gsmCell: GsmCellEntity)

    @Query("SELECT * FROM GsmCell")
    suspend fun getAllGsmCell(): List<GsmCellEntity>
}
