package com.abra.nagham.presentation.songs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SongsScreen(
    viewModel: SongsViewModel = hiltViewModel(),
    onNavigateToNowPlaying: () -> Unit
) {
    val songs = viewModel.songs.collectAsState().value

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(songs.size) { index ->
                SongItem(
                    song = songs[index],
                    onClick = {
                        viewModel.playSong(songs[index])
                        onNavigateToNowPlaying()
                    }
                )
            }
        }
    }
}
