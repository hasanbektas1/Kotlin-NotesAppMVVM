package com.hasanbektas.notesappnew.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.hasanbektas.notesappnew.model.NoteModel
import com.hasanbektas.notesappnew.repository.NoteRepository
import com.hasanbektas.notesappnew.service.NotesDatabase
import com.hasanbektas.notesappnew.view.DetailFragmentDirections
import kotlinx.coroutines.launch

class NotesViewModel (application: Application) : AndroidViewModel(application) {

    val noteMainList: LiveData<List<NoteModel>>

    private val repository: NoteRepository

    init {
        val noteDao = NotesDatabase.invoke(application).notesDao()
        repository = NoteRepository(noteDao)
        noteMainList = repository.getAllDataNote
    }


    val noteSelectedLiveData = MutableLiveData<NoteModel>()

    fun getDataFromRoom(uuid : Int) = viewModelScope.launch{

            val  dao = NotesDatabase(getApplication()).notesDao()
            val notess = dao.getNot(uuid)
        noteSelectedLiveData.value = notess
    }

    fun insertNoteData (note: NoteModel) =  viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNoteData(note: NoteModel) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun makeNoteData(title: String, note: String, date: String,view: View) {

        if (title.isEmpty() || note.isEmpty() || date.isEmpty()) {

            Toast.makeText(getApplication(),"Alanları Doldurunuz", Toast.LENGTH_LONG).show()
        } else {

            val saveNote = NoteModel(title, note, date)
            insertNoteData(saveNote)

            Toast.makeText(getApplication(),"'${title}' Eklendi", Toast.LENGTH_LONG).show()

            val action = DetailFragmentDirections.actionDetailFragmentToNotesFragment()
            Navigation.findNavController(view).navigate(action)

        }
    }
}