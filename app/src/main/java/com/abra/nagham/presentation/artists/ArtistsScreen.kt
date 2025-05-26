package com.abra.nagham.presentation.artists

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArtistsScreen(
    viewModel: ArtistsViewModel = hiltViewModel(),
    onNavigateToArtistDetail: (Long) -> Unit
) {
    val artists = viewModel.artists.collectAsState().value

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(artists.size) { index ->
                ArtistItem(
                    artist = artists[index],
                    onClick = { onNavigateToArtistDetail(artists[index].id) }
                )
            }
        }
    }
}