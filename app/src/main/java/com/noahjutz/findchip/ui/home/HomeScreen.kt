package com.noahjutz.findchip.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Findchip") },
                navigationIcon = { IconButton(onClick = {}) { Icon(Icons.Default.Bluetooth) } }
            )
        }
    ) {
        val bondedDevices by viewModel.bondedDevices.collectAsState(initial = emptyList())
        LazyColumn {
            items(bondedDevices) { device ->
                ListItem {
                    Text(device.name)
                }
            }
        }
    }
}
