package com.example.contacthub.database

import com.example.contacthub.data.Con
import kotlinx.coroutines.flow.Flow

class ConRepository(private val conDAO: ConDAO) {

    suspend fun addCon(con: Con){
        conDAO.addCon(con)
    }

    fun getCon(): Flow<List<Con>> = conDAO.getAllCon()

    fun getConById(id: Long): Flow<Con> {
        return conDAO.getConById(id)
    }

    suspend fun updateCon(con: Con){
        conDAO.updateCon(con)
    }

    suspend fun deleteCon(con: Con){
        conDAO.deleteCon(con)
    }

}