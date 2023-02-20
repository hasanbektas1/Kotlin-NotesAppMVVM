package com.hasanbektas.notesappnew.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class NoteModel (

    @ColumnInfo(name="title") var title:String,
    @ColumnInfo(name="note") var note : String,
    @ColumnInfo(name="date") var date : String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid = 0
}