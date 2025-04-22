package hanz.coding.noteapp.presentation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class NoteRoute(val id: Long)

fun NavController.navigateToNote(noteId: Long, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = NoteRoute(noteId)) {
        navOptions()
    }
}

fun NavGraphBuilder.noteScreen(
    popUpScreen: () -> Unit,
) {
    composable<NoteRoute> {
        NoteRoot(popUpScreen = popUpScreen)
    }
}