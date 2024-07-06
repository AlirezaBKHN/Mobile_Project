package com.example.mobile_project

import android.app.Application
import androidx.room.Room
import com.example.mobile_project.cellData.*
class MyApplication : Application() {




    lateinit var mainRepository: MainRepository
            private set


    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-cell-info-db"
        ).build()
        mainRepository = MainRepository(
            database.cellInfoDao(),
            database.gsmCellDao(),
            database.umtsCellDao(),
            database.lteCellDao()
        )
    }

}
