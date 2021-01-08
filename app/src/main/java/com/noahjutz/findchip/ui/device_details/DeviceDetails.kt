package com.noahjutz.findchip.ui.device_details

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DeviceDetails(
    device: BluetoothDevice,
    viewModel: DeviceDetailsViewModel = getViewModel { parametersOf(device) },
    popBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) } },
                title = { Text("DeviceDetails") }
            )
        }
    ) {
        val isConnected by viewModel.isConnected.collectAsState(false)
        when {
            !isConnected -> ConnectingAlert(popBackStack)
            else -> DeviceDetailsContent()
        }
    }
}

@Composable
private fun DeviceDetailsContent() {
    Text("Hello world!")
}

@Composable
private fun ConnectingAlert(
    popBackStack: () -> Unit
) {
    AlertDialog(
        title = { Text("Connecting...") },
        text = {
            LinearProgressIndicator(Modifier.fillMaxWidth())
        },
        dismissButton = {
            TextButton(onClick = popBackStack) {
                Text("Cancel")
            }
        },
        confirmButton = {},
        onDismissRequest = popBackStack
    )
}
