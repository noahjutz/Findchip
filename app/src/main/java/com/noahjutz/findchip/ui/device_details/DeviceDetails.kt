package com.noahjutz.findchip.ui.device_details

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DeviceDetails(
    popBackStack: () -> Unit,
    address: String,
    viewModel: DeviceDetailsViewModel = getViewModel { parametersOf(address) }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) } },
                title = { Text("DeviceDetails") }
            )
        }
    ) {
        ScrollableColumn {
            Text("Address: $address")
            Text("UUIDS: ...")
            Text("name: ...")
            Text("rssi: ...")
            Button(onClick = {/* TODO */ }) { Text("Toggle Beep") }
        }
    }
}