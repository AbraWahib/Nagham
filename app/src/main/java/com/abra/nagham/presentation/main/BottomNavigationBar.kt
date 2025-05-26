package com.abra.nagham.presentation.main

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            //TODO: Navigate to Songs
            onClick = { },
            icon = { Text("Songs") }
        )
        NavigationBarItem(
            selected = false,
            //TODO: Navigate to Artists
            onClick = { },
            icon = { Text("Artists") }
        )
        NavigationBarItem(
            selected = false,
            //TODO: Navigate to Albums
            onClick = { },
            icon = { Text("Albums") }
        )
        NavigationBarItem(
            selected = false,
            //TODO: Navigate to Playlists
            onClick = { },
            icon = { Text("Playlists") }
        )
    }
}