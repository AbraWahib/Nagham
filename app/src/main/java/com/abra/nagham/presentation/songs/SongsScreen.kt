package com.abra.nagham.presentation.songs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongsScreen(
    viewModel: SongsViewModel = hiltViewModel(),
    sheetState: SheetState,
    onShowBottomSheet: () -> Unit
) {
    val songsWithArtist = viewModel.songsWithArtist.collectAsState().value
    val scope = rememberCoroutineScope()
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(songsWithArtist.size) { index ->
                SongItem(
                    songWithArtist = songsWithArtist[index],
                    onClick = {
                        viewModel.playSong(songsWithArtist[index].song)
                        scope.launch { sheetState.expand() }
                        onShowBottomSheet()
                    }
                )
                HorizontalDivider(Modifier.padding(16.dp))
            }
        }
    }
}
