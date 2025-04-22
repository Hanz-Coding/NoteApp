package hanz.coding.noteapp.presentation.note_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import hanz.coding.noteapp.R
import hanz.coding.noteapp.domain.NOTE_DEFAULT_ID
import hanz.coding.noteapp.domain.Note
import hanz.coding.noteapp.presentation.note_list.components.ResponsiveNoteGrid
import hanz.coding.noteapp.presentation.note_list.components.SearchBar
import hanz.coding.noteapp.presentation.note_list.components.SortDropdownMenu
import hanz.coding.noteapp.presentation.note_list.components.TwoSmallButtonsRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteListRoot(
    viewmodel: NoteListViewModel = koinViewModel(),
    onNoteDetailClick: (Long) -> Unit
) {
    val notesState = viewmodel.notes.collectAsLazyPagingItems()
    val filter by viewmodel.filter.collectAsState()
    NoteListScreen(
        noteItems = notesState,
        searchText = filter.query,
        onNoteClick = onNoteDetailClick,
        onSortChanged = { sort -> viewmodel.updateSort(sort) },
        onSearchTextChanged = { query -> viewmodel.updateSearchQuery(query) },
        onPrimaryButtonClick = { viewmodel.add100Records() },
        onSecondaryButtonClick = { viewmodel.deleteAllDb() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    searchText: String,
    noteItems: LazyPagingItems<Note>,
    onNoteClick: (Long) -> Unit,
    onSortChanged: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
) {
    var selectedSortOption by remember { mutableStateOf("Latest") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNoteClick(NOTE_DEFAULT_ID) },
                modifier = modifier.padding(16.dp),
                containerColor = Color(0xFF6650a4),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchBar(
                    searchQuery = searchText,
                    onSearchQueryChange = {
                        onSearchTextChanged(it)
                    },
                    modifier = Modifier
                        .weight(1f)
                )

                SortDropdownMenu(
                    selectedOption = selectedSortOption,
                    onOptionSelected = { option ->
                        selectedSortOption = option
                        onSortChanged(option.lowercase())
                    }
                )
            }

            TwoSmallButtonsRow(
                onClickFirst = onPrimaryButtonClick,
                onClickSecond = onSecondaryButtonClick
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            ResponsiveNoteGrid(
                notes = noteItems,
                onItemClick = { id -> onNoteClick(id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    val fakeNotes = List(5) { index ->
        Note(id = index.toLong(), text = "Note #$index", updateTime = "2024-04-22")
    }

    val pagingData = Pager(PagingConfig(pageSize = 10)) {
        object : PagingSource<Int, Note>() {
            override fun getRefreshKey(state: PagingState<Int, Note>): Int? = null
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
                return LoadResult.Page(
                    data = fakeNotes,
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }.flow

    val lazyPagingItems = pagingData.collectAsLazyPagingItems()

    NoteListScreen(
        searchText = "",
        noteItems = lazyPagingItems,
        onNoteClick = {},
        onSortChanged = {},
        onSearchTextChanged = {},
        onPrimaryButtonClick = {},
        onSecondaryButtonClick = {}
    )
}