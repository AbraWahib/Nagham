package com.abra.nagham.presentation.permissions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDeniedScreen(modifier: Modifier = Modifier) {
    Row{
        Text(
            text = "Permission denied. Please grant storage permission in app settings to access music.",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}