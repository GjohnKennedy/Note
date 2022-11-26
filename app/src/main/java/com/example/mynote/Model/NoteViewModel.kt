package com.example.mynote.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynote.Database.NoteDatabase
import com.example.mynote.Database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {

    private var repository : NoteRepository

    val allNotesRep:LiveData<List<Note>>

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repository= NoteRepository(dao)
        allNotesRep=repository.allNotes
    }
    fun deleteRep(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertrep(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)

    }
    fun updaterep(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

}