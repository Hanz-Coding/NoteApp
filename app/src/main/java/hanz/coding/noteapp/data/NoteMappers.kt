package hanz.coding.noteapp.data

import hanz.coding.noteapp.domain.Note

fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id,
        text = this.text,
        userId = this.userId,
        updateTime = this.updateTime,
        bgColor = this.bgColor
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        text = this.text,
        userId = this.userId,
        updateTime = this.updateTime,
        bgColor = this.bgColor
    )
}

fun Note.toNewEntity(): NoteEntity {
    return NoteEntity(
        text = this.text,
        userId = this.userId,
        updateTime = this.updateTime,
        bgColor = this.bgColor
    )
}