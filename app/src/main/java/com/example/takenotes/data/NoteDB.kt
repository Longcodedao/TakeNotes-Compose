package com.example.takenotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.takenotes.model.Note
import com.example.takenotes.utils.DateConvert

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConvert::class)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao(): NoteDBDao
}