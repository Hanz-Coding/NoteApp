package hanz.coding.noteapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNotesPaging(): Flow<PagingData<Note>>
    fun getNote(noteId: Long): Flow<Note>
    suspend fun upsertNotes(notes: List<Note>)
    suspend fun saveNote(note: Note)
    suspend fun createNote(note: Note)
    suspend fun deleteNote(noteId: Long)
    suspend fun deleteAllNote()
    fun searchNote(query: String): Flow<PagingData<Note>>
    fun getFilteredNotes(query: String, sort: String): Flow<PagingData<Note>>
}