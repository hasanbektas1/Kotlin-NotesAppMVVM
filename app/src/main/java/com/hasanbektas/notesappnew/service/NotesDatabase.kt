package com.hasanbektas.notesappnew.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hasanbektas.notesappnew.model.NoteModel


@Database(entities = [NoteModel::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {

        @Volatile
        private var instance: NotesDatabase? = null

        private val lock = Any()

        operator fun invoke(contex: Context) = instance ?: synchronized(lock) {

            instance ?: buildDatabase(contex).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, NotesDatabase::class.java, "dbNotes"
        ).build()
    }

}