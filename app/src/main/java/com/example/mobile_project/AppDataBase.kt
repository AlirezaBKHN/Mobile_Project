package com.example.mobile_project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_project.cellData.*

@Database(
    entities = [CellInfoEntity::class, GsmCellEntity::class, UmtsCellEntity::class, LteCellEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cellInfoDao(): CellInfoDao
    abstract fun gsmCellDao(): GsmCellDao
    abstract fun umtsCellDao(): UmtsCellDao
    abstract fun lteCellDao(): LteCellDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
