package hanz.coding.noteapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteDAO: NoteDAO) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDAO.getNotes().filterNotNull()
            .map { value: List<NoteEntity> -> value.map { entity -> entity.toDomain() } }
    }

    override fun getNotesPaging(): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { noteDAO.getNotesPaging() }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override fun getNote(noteId: Long): Flow<Note> {
        return noteDAO.getNote(noteId).filterNotNull().map { entity ->
            entity.toDomain()
        }
    }

    override suspend fun upsertNotes(notes: List<Note>) {
        noteDAO.upsertNotes(notes.map { it.toEntity() })
    }

    override suspend fun saveNote(note: Note) {
        noteDAO.updateNote(note.toEntity())
    }

    override suspend fun createNote(note: Note) {
        noteDAO.createNote(note.toNewEntity())
    }

    override suspend fun deleteNote(noteId: Long) {
        noteDAO.deleteNote(noteId)
    }

    override suspend fun deleteAllNote() {
        noteDAO.deleteAllNote()
    }

    override fun searchNote(query: String): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { noteDAO.searchNotes(query) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun getFilteredNotes(query: String, sort: String): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { noteDAO.getFilteredPaging(query, sort) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}