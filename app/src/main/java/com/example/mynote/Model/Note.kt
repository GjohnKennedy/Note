package com.example.mynote.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "Title") var title:String?,
    @ColumnInfo(name = "Content") var content:String?,
    @ColumnInfo(name = "Date") var date:String?
):java.io.Serializable