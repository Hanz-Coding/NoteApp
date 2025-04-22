package hanz.coding.noteapp.domain

import com.google.firebase.firestore.DocumentId

private const val TITLE_MAX_SIZE = 30
const val NOTE_DEFAULT_ID = -1L

data class Note(
    @DocumentId val id: Long = NOTE_DEFAULT_ID,
    val text: String = "My Note",
    val userId: String = "",
    val updateTime: String = "",
    val bgColor: Int = 0xFFB3E5FC.toInt()
)

fun Note.getTitle(): String {
    val isLongText = this.text.length > TITLE_MAX_SIZE
    val endRange = if (isLongText) TITLE_MAX_SIZE else this.text.length - 1
    return this.text.substring(IntRange(0, endRange))
}