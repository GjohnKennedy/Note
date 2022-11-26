package com.example.mynote.Database

import androidx.lifecycle.LiveData
import com.example.mynote.Model.Note

class NoteRepository(private val noteDao:NoteDao) {

    val allNotes:LiveData<List<Note>> = noteDao.getAllnote()

    suspend fun insert(note:Note)
    {
        noteDao.insert(note)
    }
    suspend fun delete(note:Note)
    {
        noteDao.delete(note)
    }
    suspend fun update(note:Note)
    {
        noteDao.update(id = note.id, title = note.title, content = note.content)
    }
}