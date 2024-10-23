package com.example.contacthub.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contacthub.data.Con
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ConDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addCon(conEntity: Con)

    @Query("Select * from `con-table`")
    abstract fun getAllCon(): Flow<List<Con>>

    @Update
    abstract fun updateCon(conEntity: Con)

    @Delete
    abstract fun deleteCon(conEntity: Con)

    @Query("Select * from `con-table` where id=:id")
    abstract fun getConById(id: Long): Flow<Con>


}