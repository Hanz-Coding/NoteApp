package hanz.coding.noteapp.presentation.note_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object NoteListRoute

fun NavGraphBuilder.noteListScreen(
    onNoteDetailClick: (Long) -> Unit,
) {
    composable<NoteListRoute> {
        NoteListRoot(onNoteDetailClick = onNoteDetailClick)
    }
}