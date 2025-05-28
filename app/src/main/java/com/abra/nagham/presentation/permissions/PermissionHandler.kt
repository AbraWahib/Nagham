package com.abra.nagham.presentation.permissions

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.abra.nagham.util.hasAudioPermission

@Composable
fun PermissionHandler(
    onPermissionDenied: @Composable () -> Unit = { PermissionDeniedScreen() },
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val permission = if (android.os.Build.VERSION.SDK_INT >= 33) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var isPermissionGranted by remember { mutableStateOf(hasAudioPermission(context)) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            isPermissionGranted = granted
            if(!granted){
                showPermissionDialog = true
            }
        }
    LaunchedEffect(Unit) {
        if (!isPermissionGranted) launcher.launch(permission)
    }
    when {
        isPermissionGranted -> content()
        showPermissionDialog -> {
            AlertDialog(
                onDismissRequest = { showPermissionDialog = false },
                title = { Text("Permission Required") },
                text = { Text("This app needs storage permission to access music files.") },
                confirmButton = {
                    Button(onClick = {
                        launcher.launch(permission)
                        showPermissionDialog = false
                    }) {
                        Text("Grant")
                    }
                },
                dismissButton = {
                    Button(onClick = { showPermissionDialog = false }) {
                        Text("Deny")
                    }
                },
                modifier = Modifier.background(Color.Black.copy(alpha = .7f))
            )
        }
        !isPermissionGranted && showPermissionDialog-> onPermissionDenied()
        else -> {}
    }
}
