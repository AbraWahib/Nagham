package com.abra.nagham.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abra.nagham.presentation.albums.AlbumsScreen
import com.abra.nagham.presentation.artists.ArtistsScreen
import com.abra.nagham.presentation.playlist.PlaylistsScreen
import com.abra.nagham.presentation.songs.SongsScreen
import com.abra.nagham.presentation.songs.SongsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = {true}
    )
    if (showBottomSheet)
    NavHost(navController = navController, startDestination = Screen.Songs.route) {
        composable(Screen.Songs.route) {
            val viewModel:SongsViewModel = hiltViewModel()
            SongsScreen(
                onShowBottomSheet = { showBottomSheet = true},
                viewModel = viewModel,
                sheetState = sheetState
            )
        }
        composable(Screen.Artists.route) {
            ArtistsScreen(
                onNavigateToArtistDetail = { artistId ->
                    navController.navigate("artist/$artistId")
                }
            )
        }
        composable(Screen.Albums.route) {
            AlbumsScreen(
                onNavigateToAlbumDetail = { albumId ->
                    navController.navigate("album/$albumId")
                }
            )
        }
        composable(Screen.Playlists.route) {
            PlaylistsScreen (
                onNavigateToPlaylistDetail = { playlistId ->
                    navController.navigate("playlist/$playlistId")
                }
            )
        }
    }
}