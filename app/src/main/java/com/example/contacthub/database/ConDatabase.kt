package com.example.contacthub.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacthub.data.Con

@Database(
    entities = [Con::class],
    version = 1,
    exportSchema = false
)
abstract class ConDatabase:RoomDatabase() {

    abstract fun conDao(): ConDAO

}