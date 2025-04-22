package hanz.coding.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hanz.coding.noteapp.navigation.NoteNavHost
import hanz.coding.noteapp.ui.theme.NoteAppTheme

@Composable
fun NotesApp() {

    val appState = rememberAppState()
    NoteAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NoteNavHost(
                    appState,
                )
            }
        }
    }
}