package com.example.mobile_project.cellData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "GsmCell")
data class GsmCellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val rxLev: Int,
    val C1: Int,
    val C2: Int,
    val PLMN: String,
    val LAC: String,
    val lat: Double,
    val lon: Double
)