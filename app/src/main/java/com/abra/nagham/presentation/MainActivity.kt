package com.abra.nagham.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.abra.nagham.presentation.main.NaghamApp
import com.abra.nagham.presentation.permissions.PermissionHandler
import com.abra.nagham.presentation.ui.theme.NaghamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NaghamTheme {
                PermissionHandler{
                    Scaffold {
                        NaghamApp(modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}


