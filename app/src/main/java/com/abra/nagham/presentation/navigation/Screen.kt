package com.abra.nagham.presentation.navigation

sealed class Screen(val route: String, val title:String) {
    data object Songs : Screen("songs","Songs")
    data object Artists : Screen("Artists","Artists")
    data object Albums : Screen("Albums","Albums")
    data object Playlists : Screen("Playlists","Playlists")
    data object NowPlaying : Screen("now_playing","now_playing")
    companion object{
        val tabs = listOf(Songs, Artists, Albums, Playlists)
    }
}