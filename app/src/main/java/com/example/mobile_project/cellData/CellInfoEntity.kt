package com.example.mobile_project.cellData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cell_info")
data class CellInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val info: String,
    val timestamp: Long = System.currentTimeMillis()
)





