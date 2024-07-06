package com.example.mobile_project.cellData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UmtsCellDao{
    @Insert
    suspend fun insert(umtsCell: UmtsCellEntity)
    @Query("SELECT * FROM UmtsCell")
    suspend fun getAllUmtsCell(): List<UmtsCellEntity>
}