package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.noahjutz.findchip.R
import org.koin.androidx.compose.getViewModel

@Composable
fun DeviceList(
    viewModel: DeviceListViewModel = getViewModel(),
    navToDeviceDetails: (BluetoothDevice) -> Unit
) {
    val isScanning by viewModel.isScanning.collectAsState()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("DeviceList") },
                    navigationIcon = { IconButton(onClick = {}) { Image(vectorResource(id = R.drawable.ic_launcher_foreground)) } },
                    actions = { IconButton(onClick = { viewModel.scan() }) { Icon(Icons.Default.Refresh) } }
                )
                if (isScanning) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
            }
        }
    ) {
        val devices by viewModel.devices.collectAsState()
        if (devices.isNotEmpty()) {
            LazyColumn {
                items(devices) { device ->
                    ListItem(
                        Modifier.clickable { navToDeviceDetails(device) },
                        text = {
                            Text(device.name?.takeIf { it.isNotBlank() } ?: "Unnamed Device")
                        },
                        secondaryText = { Text(device.address.toString()) }
                    )
                }
            }
        } else NoDevicesFound(scan = { viewModel.scan() })
    }
}

@Composable
private fun NoDevicesFound(
    scan: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("No devices found yet.")
            Spacer(Modifier.preferredHeight(8.dp))
            Button(onClick = scan) {
                Icon(Icons.Default.Refresh)
                Text("Scan again")
            }
        }
    }
}
