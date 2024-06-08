package com.example.takenotes.data

import com.example.takenotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDBDao: NoteDBDao
) {
    suspend fun addNote(note: Note) = noteDBDao.insert(note)
    suspend fun updateNote(note: Note) = noteDBDao.update(note)
    suspend fun deleteNote(note: Note) = noteDBDao.delete(note)
    suspend fun deleteAll() = noteDBDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDBDao.getNotes().flowOn(Dispatchers.IO)

}