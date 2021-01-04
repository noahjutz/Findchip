package com.noahjutz.findchip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.ui.platform.setContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Scaffold(
                        topBar = {
                            TopAppBar(
                                    title = { Text("Findchip") },
                                    navigationIcon = {IconButton(onClick = {}) {Icon(Icons.Default.Bluetooth)} }
                            )
                        }
                ) {
                    NavGraph()
                }
            }
        }
    }
}

