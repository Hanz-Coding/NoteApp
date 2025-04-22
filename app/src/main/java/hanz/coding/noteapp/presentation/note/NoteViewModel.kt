package hanz.coding.noteapp.presentation.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import hanz.coding.noteapp.domain.NOTE_DEFAULT_ID
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.domain.NoteRepository
import hanz.coding.noteapp.presentation.getCurrentTime
import hanz.coding.noteapp.presentation.getRandomColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository
) : ViewModel() {

    private val noteId = savedStateHandle.toRoute<NoteRoute>().id

    private val _note = MutableStateFlow(Note())
    val note = _note.onStart {
        getNote(noteId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Note())

    private fun getNote(noteId: Long) {
        if (noteId != NOTE_DEFAULT_ID) {
            viewModelScope.launch {
                repository.getNote(noteId).collect { item ->
                    _note.value = item
                }
            }
        }
    }

    fun updateNote(newText: String) {
        _note.value = _note.value.copy(text = newText)
    }

    fun saveNote(popUpScreen: () -> Unit) {
        viewModelScope.launch {
            if (_note.value.id == NOTE_DEFAULT_ID) {
                _note.update {
                    it.copy(
                        updateTime = getCurrentTime(),
                        bgColor = getRandomColor()
                    )
                }
                repository.createNote(_note.value)
            } else {
                repository.saveNote(_note.value)
            }
        }
        popUpScreen()
    }

    fun deleteNote(popUpScreen: () -> Unit) {
        viewModelScope.launch {
            repository.deleteNote(note.value.id)
        }
        popUpScreen()
    }

}