package com.example.mobile_project.cellData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UmtsCell")
data class UmtsCellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val RSCP: Int,
    val ECN0: Int,
    val PLMN: String,
    val LAC: String,
    val lat: Double,
    val lon: Double
)