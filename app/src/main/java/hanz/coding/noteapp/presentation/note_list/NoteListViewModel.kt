package hanz.coding.noteapp.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.domain.NoteRepository
import hanz.coding.noteapp.presentation.getRandomColor
import hanz.coding.noteapp.presentation.getTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NoteListViewModel(
    private val repository: NoteRepository,
) : ViewModel() {

    private val _filter = MutableStateFlow(NoteFilter())
    val filter: StateFlow<NoteFilter> = _filter

    val notes = filter
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { filter ->
            repository.getFilteredNotes(filter.query, filter.sort)
        }
        .cachedIn(viewModelScope)

    fun updateSearchQuery(query: String) {
        _filter.update { it.copy(query = query) }
    }

    fun updateSort(sort: String) {
        _filter.update { it.copy(sort = sort) }
    }

    fun add100Records() {
        viewModelScope.launch {
            repository.upsertNotes(get1000NoteList())
        }
    }

    fun deleteAllDb() {
        viewModelScope.launch {
            repository.deleteAllNote()
        }
    }

    private fun get1000NoteList(): List<Note> {
        val notes: MutableList<Note> = mutableListOf()
        for (i in 0..1000) {
            notes.add(
                Note(
                    id = 0,
                    text = "Note $i",
                    userId = "user_$i",
                    updateTime = getTime(i),
                    bgColor = getRandomColor()
                )
            )
        }
        return notes
    }
}

data class NoteFilter(
    val query: String = "",
    val sort: String = "latest"
)