package com.example.mobile_project.cellData

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "LteCell")
data class LteCellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val RSRP: Int,
    val RSRQ: Int,
    val CINR: Int,
    val PLMN: String,
    val TAC: String,
    val lat: Double,
    val lon: Double
)