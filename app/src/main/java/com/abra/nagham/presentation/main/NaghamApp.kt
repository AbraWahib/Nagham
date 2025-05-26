package com.abra.nagham.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abra.nagham.presentation.navigation.NavGraph
import com.abra.nagham.presentation.navigation.Screen
import com.abra.nagham.presentation.ui.theme.NaghamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NaghamApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    Column(modifier.fillMaxSize()) {
        PrimaryScrollableTabRow(
            selectedTabIndex = Screen.tabs.indexOfFirst { it.route == currentRoute },
            modifier = modifier
        ) {
            Screen.tabs.forEachIndexed { index, screen ->
                Tab(
                    selected = currentRoute == screen.route,
                    onClick = {
                        if(currentRoute != screen.route){
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.startDestinationId){saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    text = { Text(text = screen.title) }
                )
            }
        }
        NavGraph(navController)
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun NaghamAppPrev() {
    NaghamTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            NaghamApp()
        }
    }
}