package com.abra.nagham.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abra.nagham.presentation.albums.AlbumsScreen
import com.abra.nagham.presentation.artists.ArtistsScreen
import com.abra.nagham.presentation.playing.NowPlayingScreen
import com.abra.nagham.presentation.playlist.PlaylistsScreen
import com.abra.nagham.presentation.songs.SongsScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Songs.route) {
        composable(Screen.Songs.route) {
            SongsScreen(
                onNavigateToNowPlaying = { navController.navigate("now_playing") }
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
        composable("now_playing") {
            NowPlayingScreen()
        }
        // Placeholder for detail screens
        composable("artist/{artistId}") {
            //TODO: ArtistDetailScreen(backStackEntry.arguments?.getString("artistId")?.toLong() ?: 0)
        }
        composable("album/{albumId}") {
            //TODO: AlbumDetailScreen(backStackEntry.arguments?.getString("albumId")?.toLong() ?: 0)
        }
        composable("playlist/{playlistId}") {
            //TODO: PlaylistDetailScreen(backStackEntry.arguments?.getString("playlistId")?.toLong() ?: 0)
        }
    }
}