package com.abra.nagham.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.abra.nagham.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayout(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    selectedTab = when (currentRoute) {
        Screen.Songs.route -> 0
        Screen.Artists.route -> 1
        Screen.Albums.route -> 2
        Screen.Playlists.route -> 3
        else -> 0
    }
    val tabs = listOf("Songs", "Artists", "Albums", "Playlists")
    SecondaryTabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier
    ) {
        tabs.forEachIndexed {index, title ->
            Tab(
                selected = index == selectedTab,
                onClick = {
                    when(index){
                        0->{
                            navToTab(navController,Screen.Songs.route)
                        }
                        1->{
                            navToTab(navController,Screen.Artists.route)
                        }
                        2->{
                            navToTab(navController,Screen.Albums.route)
                        }
                        3->{
                            navToTab(navController,Screen.Playlists.route)
                        }

                        else ->{}
                    }
                },
                text = { Text(text = title) }
            )
        }
    }
}
private fun navToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}



