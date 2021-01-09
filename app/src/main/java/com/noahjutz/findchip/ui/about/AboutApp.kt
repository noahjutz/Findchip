package com.noahjutz.findchip.ui.about

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AboutApp(
    popBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack)}
                },
                title = { Text("About") }
            )
        }
    ) {
        Text("About App. TODO")
    }
}