package com.example.contacthub.graph

import android.content.Context
import androidx.room.Room
import com.example.contacthub.database.ConDatabase
import com.example.contacthub.database.ConRepository

object Graph {

    lateinit var database: ConDatabase

    val conRepository by lazy {
        ConRepository(conDAO = database.conDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, ConDatabase::class.java,"contacts.db").build()
    }

}