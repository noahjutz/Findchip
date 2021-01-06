package com.noahjutz.findchip.ui.device_details

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun DeviceDetails(
    popBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) } },
                title = { Text("DeviceDetails") }
            )
        }
    ) {
        Text("TODO create layout")
    }
}