package hanz.coding.noteapp.presentation.note_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwoSmallButtonsRow(
    onClickFirst: () -> Unit,
    onClickSecond: () -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onClickFirst,
            modifier = Modifier
                .height(36.dp)
                .defaultMinSize(minWidth = 64.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(text = "Add 1000 records", fontSize = 12.sp)
        }

        Button(
            onClick = onClickSecond,
            modifier = Modifier
                .height(36.dp)
                .defaultMinSize(minWidth = 64.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(text = "Clear DB", fontSize = 12.sp)
        }
    }
}