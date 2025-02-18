package com.app.footballappwithjetpack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.footballappwithjetpack.model.Club

@Database(entities = [Club::class], version = 2, exportSchema = true)
abstract class ClubDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao

    companion object {
        @Volatile
        private var INSTANCE: ClubDatabase? = null

        fun getInstance(context: Context): ClubDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClubDatabase::class.java,
                    "club_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


