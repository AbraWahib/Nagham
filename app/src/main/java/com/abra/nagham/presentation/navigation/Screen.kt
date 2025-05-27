package com.abra.nagham.presentation.navigation

sealed class Screen(val route: String) {
    data object Songs : Screen("songs")
    data object Artists : Screen("Artists")
    data object Albums : Screen("Albums")
    data object Playlists : Screen("Playlists")
    data object NowPlaying : Screen("now_playing")
}