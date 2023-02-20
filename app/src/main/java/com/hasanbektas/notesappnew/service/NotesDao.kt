package com.hasanbektas.notesappnew.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hasanbektas.notesappnew.model.NoteModel

@Dao
interface NotesDao {

    @Insert
    suspend fun insertNot(notes: NoteModel)

    @Delete
    suspend fun deleteNot(notes:NoteModel)

    @Query("SELECT * FROM notes_table")
    fun observeNotLiveData() : LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE uuid = :noteId")
    suspend fun getNot(noteId : Int):NoteModel

}