package com.example.mynote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynote.Model.Note
import com.example.mynote.utilitils.DATABASE_NAME


@Database(entities = arrayOf(Note::class),version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase()  {

  abstract fun getNoteDao():NoteDao

  companion object{

      @Volatile
      var INSTANCE:NoteDatabase?=null

      fun getDatabase(context: Context):NoteDatabase{
          return INSTANCE?: synchronized(this)
          {
              var instence=Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,DATABASE_NAME).build()

              INSTANCE=instence
              instence
          }
      }

  }
}