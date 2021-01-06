package com.noahjutz.findchip.ui.device_list

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import com.noahjutz.findchip.R
import org.koin.androidx.compose.getViewModel

@Composable
fun DeviceList(
    viewModel: DeviceListViewModel = getViewModel(),
    navToDeviceDetails: (String) -> Unit
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
        LazyColumn {
            items(devices) { device ->
                ListItem(
                    Modifier.clickable { navToDeviceDetails(device.address) },
                    text = { Text(device.name?.takeIf { it.isNotBlank() } ?: "Unnamed Device") },
                    secondaryText = {
                        Column {
                            Text("Address: ${device.address}")
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                                Text("Alias: ${device.alias}")
                            Text("BT Class: ${device.bluetoothClass.majorDeviceClass} / ${device.bluetoothClass.deviceClass}")
                            Text("Bond State: ${device.bondState}")
                            Text("Type: ${device.type}")
                            Text("UUIDs: ${device.uuids}")
                        }
                    }
                )
            }
        }
    }
}