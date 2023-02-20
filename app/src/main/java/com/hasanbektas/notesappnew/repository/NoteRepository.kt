package com.hasanbektas.notesappnew.repository

import androidx.lifecycle.LiveData
import com.hasanbektas.notesappnew.model.NoteModel
import com.hasanbektas.notesappnew.service.NotesDao

class NoteRepository (private val noteDao : NotesDao) {

    val getAllDataNote : LiveData<List<NoteModel>> = noteDao.observeNotLiveData()

    suspend fun insertNote(note : NoteModel) {

        noteDao.insertNot(note)
    }

    suspend fun deleteNote(note: NoteModel) {

        noteDao.deleteNot(note)
    }
}