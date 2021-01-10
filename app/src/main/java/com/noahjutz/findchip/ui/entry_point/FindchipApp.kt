package com.noahjutz.findchip.ui.entry_point

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.noahjutz.findchip.ui.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun FindchipApp(
    viewModel: FindchipAppViewModel = getViewModel(),
    requestLocationPermission: () -> Unit,
    openUrl: (String) -> Unit,
    enableLocation: () -> Unit
) {
    Scaffold {
        val isBluetoothDisabled by viewModel.isBluetoothDisabled.collectAsState(initial = true)
        val isLocationPermissionMissing by viewModel.isLocationPermMissing.collectAsState(initial = true)
        val isLocationDisabled by viewModel.isLocationDisabled.collectAsState(initial = true)
        when {
            isBluetoothDisabled -> BluetoothAlert { viewModel.enableBluetooth() }
            isLocationPermissionMissing -> LocationPermissionAlert(requestLocationPermission)
            isLocationDisabled -> LocationDisabledAlert(enableLocation)
            else -> NavGraph(openUrl)
        }
    }
}

@Composable
private fun BluetoothAlert(
    enableBluetooth: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = { Button(onClick = enableBluetooth) { Text("Enable Bluetooth") } },
        title = { Text("Bluetooth is disabled.") },
        text = { Text("Bluetooth is required.") },
    )
}

@Composable
private fun LocationPermissionAlert(
    requestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = { Button(onClick = requestPermission) { Text("Grant permission") } },
        title = { Text("Location permission is missing.") },
        text = { Text("Location permission is required for Bluetooth Low Energy. If the button doesn't work, please go to App Info > Permissions > Location > Allow only while using the app.") },
    )
}

@Composable
private fun LocationDisabledAlert(
    enableLocation: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = { Button(onClick = enableLocation) { Text("Enable Location") } },
        title = { Text("Location services are disabled.") },
        text = { Text("Location services are required for scanning for BLE devices.") },
    )
}
