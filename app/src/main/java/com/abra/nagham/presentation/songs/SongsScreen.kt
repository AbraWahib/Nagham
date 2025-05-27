package com.abra.nagham.presentation.songs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SongsScreen(
    viewModel: SongsViewModel = hiltViewModel(),
    onNavigateToNowPlaying: () -> Unit
) {
    val songsWithArtist = viewModel.songsWithArtist.collectAsState().value

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
                        onNavigateToNowPlaying()
                    }
                )
                HorizontalDivider(Modifier.padding(16.dp))
            }
        }
    }
}
