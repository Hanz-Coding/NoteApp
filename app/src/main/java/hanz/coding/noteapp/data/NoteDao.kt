package hanz.coding.noteapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Update
    suspend fun updateNote(note: NoteEntity)

    @Upsert
    suspend fun upsertNotes(notes: List<NoteEntity>)

    @Insert
    suspend fun createNote(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    fun getNote(id: Long): Flow<NoteEntity>

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query("DELETE FROM NoteEntity")
    suspend fun deleteAllNote()

    @Query("SELECT * FROM NoteEntity ORDER BY updateTime DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity ORDER BY updateTime DESC")
    fun getNotesPaging(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE text LIKE '%' || :query || '%' ORDER BY updateTime DESC")
    fun searchNotes(query: String): PagingSource<Int, NoteEntity>

    @Query("""
        SELECT * FROM NoteEntity
        WHERE text LIKE '%' || :query || '%'
        ORDER BY 
            CASE WHEN :sort = 'latest' THEN updateTime END DESC,
            CASE WHEN :sort = 'oldest' THEN updateTime END ASC
    """)
    fun getFilteredPaging(query: String, sort: String): PagingSource<Int, NoteEntity>
}