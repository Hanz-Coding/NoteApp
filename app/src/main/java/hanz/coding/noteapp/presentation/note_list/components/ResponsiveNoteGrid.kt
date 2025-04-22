package hanz.coding.noteapp.presentation.note_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.presentation.note_list.NoteItem

@Composable
fun ResponsiveNoteGrid(
    notes: LazyPagingItems<Note>,
    onItemClick: (Long) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val columns = when {
        screenWidthDp < 600 -> 1
        screenWidthDp < 840 -> 2
        else -> 3
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes.itemCount) { index ->
            val note = notes[index]
            note?.let {
                NoteItem(
                    note = note,
                    onActionClick = { onItemClick(note.id) }
                )
            }
        }
    }
}