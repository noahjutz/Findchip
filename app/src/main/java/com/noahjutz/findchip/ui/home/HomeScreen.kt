package com.noahjutz.findchip.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.viewinterop.viewModel
import com.noahjutz.findchip.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Findchip") },
                navigationIcon = { IconButton(onClick = {}) { Icon(vectorResource(id = R.drawable.ic_launcher_foreground)) } }
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
