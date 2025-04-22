package hanz.coding.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "userId")
    val userId: String,
    @ColumnInfo(name = "updateTime")
    val updateTime: String,
    @ColumnInfo(name = "color")
    val bgColor: Int,
)