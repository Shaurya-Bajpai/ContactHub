package com.example.contacthub.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName = "con-table")
data class Con(
    @PrimaryKey( autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo( name = "con-title")
    val title: String = "",
    @ColumnInfo( name = "con-desc")
    val description: String = "",
)

object DummyWish{
    val wishList = listOf(
        Con(title = "Hello", description = "Intro"),
        Con(title = "Hi", description = "Second"),
        Con(title = "Good", description = "Third"),
        Con(title = "Bye", description = "Last")
    )
}

