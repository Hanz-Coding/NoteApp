package hanz.coding.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDAO: NoteDAO

    companion object {
        const val DB_NAME = "note.db"
    }
}