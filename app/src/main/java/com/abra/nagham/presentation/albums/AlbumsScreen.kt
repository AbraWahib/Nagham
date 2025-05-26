package com.abra.nagham.presentation.albums

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel(),
    onNavigateToAlbumDetail: (Long) -> Unit
) {
    val albums = viewModel.albums.collectAsState().value

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(albums.size) { index ->
                AlbumsItem(
                    album = albums[index],
                    onClick = { onNavigateToAlbumDetail(albums[index].id) }
                )
            }
        }
    }
}