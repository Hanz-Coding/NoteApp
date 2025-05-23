@file:OptIn(ExperimentalMaterial3Api::class)

package hanz.coding.noteapp.presentation.note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hanz.coding.noteapp.domain.NOTE_DEFAULT_ID
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.domain.getTitle
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRoot(
    popUpScreen: () -> Unit,
    viewModel: NoteViewModel = koinViewModel(),
) {
    val note by viewModel.note.collectAsState()
    NoteScreen(
        note = note,
        saveNote = { viewModel.saveNote(popUpScreen) },
        deleteNote = { viewModel.deleteNote(popUpScreen) },
        updateNote = { text -> viewModel.updateNote(text) }
    )
}

@Composable
fun NoteScreen(
    note: Note,
    saveNote: () -> Unit,
    deleteNote: () -> Unit,
    updateNote: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = note.getTitle(),
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false,
                    maxLines = 1
                )
            },
            actions = {
                IconButton(onClick = { saveNote() }) {
                    Icon(Icons.Filled.Done, "Save note")
                }
                IconButton(onClick = { deleteNote() }) {
                    Icon(Icons.Filled.Delete, "Delete note")
                }
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .background(Color(note.bgColor)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = note.text,
                onValueChange = { updateNote(it) },
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotePreview() {
    NoteScreen(
        mockNote, {}, {}, updateNote = { }
    )
}

val mockNote = Note(
    id = NOTE_DEFAULT_ID,
    text = "Note",
    updateTime = "12/23/56"
)