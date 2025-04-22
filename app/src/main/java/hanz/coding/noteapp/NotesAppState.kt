package hanz.coding.noteapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): NotesAppState {
    return remember(navController) {
        NotesAppState(navController = navController)
    }
}

class NotesAppState(
    val navController: NavHostController,
) {
    fun popUp() {
        navController.popBackStack()
    }
}