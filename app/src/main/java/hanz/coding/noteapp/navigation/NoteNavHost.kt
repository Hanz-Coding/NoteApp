package hanz.coding.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import hanz.coding.noteapp.NotesAppState
import hanz.coding.noteapp.presentation.note.navigateToNote
import hanz.coding.noteapp.presentation.note.noteScreen
import hanz.coding.noteapp.presentation.note_list.NoteListRoute
import hanz.coding.noteapp.presentation.note_list.noteListScreen

@Composable
fun NoteNavHost(
    appState: NotesAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = NoteListRoute,
        modifier = modifier
    ) {
        noteScreen(
            popUpScreen = { appState.popUp() }
        )

        noteListScreen(
            onNoteDetailClick = navController::navigateToNote
        )
    }
}