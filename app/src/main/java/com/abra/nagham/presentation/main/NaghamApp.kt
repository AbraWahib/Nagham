package com.abra.nagham.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abra.nagham.presentation.main.components.TabLayout
import com.abra.nagham.presentation.navigation.NavGraph

@Composable
fun NaghamApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackState?.destination?.route
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                "Nagham",
                style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(16.dp)
            )
        }
    ) {
        Column(Modifier.padding(it)) {
            TabLayout(navController, currentRoute)
            NavGraph(navController)
        }
    }

}



