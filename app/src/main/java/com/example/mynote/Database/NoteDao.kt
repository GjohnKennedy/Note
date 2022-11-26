package com.example.mynote.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mynote.Model.Note


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)



    @Query("SELECT * FROM note_table order by id  ASC")
    fun getAllnote():LiveData<List<Note>>


    @Query("UPDATE note_table SET title= :title ,content=:content WHERE id=:id")
    suspend fun  update(id:Int?,title:String?,content:String?)
}