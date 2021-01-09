package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.noahjutz.findchip.R
import org.koin.androidx.compose.getViewModel

@Composable
fun DeviceList(
    viewModel: DeviceListViewModel = getViewModel(),
    navToDeviceDetails: (BluetoothDevice) -> Unit,
    navToAboutApp: () -> Unit,
) {
    val isScanning by viewModel.isScanning.collectAsState()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Available Devices") },
                    navigationIcon = { IconButton(onClick = {}) { Image(vectorResource(id = R.drawable.ic_launcher_foreground)) } },
                    actions = {
                        IconButton(onClick = { viewModel.scan() }) { Icon(Icons.Default.Refresh) }
                        var expanded by remember { mutableStateOf(false) }
                        DropdownMenu(
                            toggle = {
                                IconButton(onClick = { expanded = true }) {
                                    Icon(Icons.Default.MoreVert)
                                }
                            },
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(onClick = navToAboutApp) {
                                Row {
                                    Icon(Icons.Default.Info)
                                    Spacer(Modifier.preferredWidth(8.dp))
                                    Text("About")
                                }
                            }
                        }
                    }
                )
                if (isScanning) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
            }
        }
    ) {
        val namedDevices by viewModel.namedDevices.collectAsState(emptyList())
        val unnamedDevices by viewModel.unnamedDevices.collectAsState(emptyList())
        if (namedDevices.isNotEmpty() || unnamedDevices.isNotEmpty()) {
            LazyColumn {
                items(namedDevices) { device ->
                    ListItem(
                        Modifier.clickable { navToDeviceDetails(device) },
                        text = { Text(device.name) },
                        secondaryText = { Text(device.address.toString()) }
                    )
                }
                item {
                    Divider()
                }
                items(unnamedDevices) { device ->
                    ListItem(
                        Modifier.clickable { navToDeviceDetails(device) }.alpha(0.5f),
                        text = { Text("Unknown") },
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
