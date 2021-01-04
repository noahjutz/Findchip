package com.noahjutz.findchip.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val isBluetoothEnabled by viewModel.isBluetoothEnabled.collectAsState(initial = false)
    if (isBluetoothEnabled) BondedDeviceList(viewModel)
    else BluetoothDisabledAlert(viewModel)
}

@Composable
fun BondedDeviceList(viewModel: HomeViewModel) {
    val bondedDevices by viewModel.bondedDevices.collectAsState(initial = emptyList())
    LazyColumn {
        items(bondedDevices) { device ->
            ListItem {
                Text(device.name)
            }
        }
    }
}

@Composable
fun BluetoothDisabledAlert(
    viewModel: HomeViewModel
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var showProgressIndicator by remember { mutableStateOf(false) }

        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Icon(Icons.Default.BluetoothDisabled)
                    Spacer(Modifier.preferredWidth(8.dp))
                    Text("Bluetooth is disabled")
                }

                Spacer(Modifier.preferredHeight(24.dp))

                Button(
                    onClick = {
                        showProgressIndicator = true
                        viewModel.enableBluetooth()
                    }
                ) {
                    Text("Enable Bluetooth")
                }
            }
        }

        if (showProgressIndicator) {
            LinearProgressIndicator(Modifier.padding(bottom = 16.dp))
        }
    }
}
