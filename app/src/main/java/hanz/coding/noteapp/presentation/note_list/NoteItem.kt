package hanz.coding.noteapp.presentation.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.domain.getTitle
import hanz.coding.noteapp.presentation.note.mockNote

@Composable
fun NoteItem(
    note: Note,
    onActionClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(note.bgColor)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onActionClick(note.id) }
        ) {
            Text(
                text = note.getTitle(),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = note.updateTime,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun NoteItemPreview(modifier: Modifier = Modifier) {
    NoteItem(mockNote) { }
}