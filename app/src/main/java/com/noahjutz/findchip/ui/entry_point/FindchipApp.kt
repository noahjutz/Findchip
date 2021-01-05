package com.noahjutz.findchip.ui.entry_point

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.viewModel
import com.noahjutz.findchip.ui.NavGraph

@Composable
fun FindchipApp(
    viewModel: FindchipAppViewModel = viewModel()
) {
    Scaffold {
        NavGraph()
        val isBluetoothEnabled by viewModel.isBluetoothEnabled.collectAsState(initial = true)
        if (!isBluetoothEnabled) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = { /*TODO*/ }) {
                        Text("Enable Bluetooth")
                    }
                },
                title = {
                    Text("Bluetooth is disabled.")
                },
                text = {
                    Text("Bluetooth is required.")
                },
            )
        }
    }
}