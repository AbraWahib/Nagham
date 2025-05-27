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
    val artistsWithDetails = viewModel.artistsWithDetails.collectAsState().value

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(artistsWithDetails.size) { index ->
                ArtistItem(
                    artistWithDetails = artistsWithDetails[index],
                    onClick = { onNavigateToArtistDetail(artistsWithDetails[index].artist.id) }
                )
            }
        }
    }
}