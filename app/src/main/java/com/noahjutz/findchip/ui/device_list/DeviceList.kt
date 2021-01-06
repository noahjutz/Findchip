package com.noahjutz.findchip.ui.device_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.viewinterop.viewModel
import com.noahjutz.findchip.R

@Composable
fun DeviceList(
    viewModel: DeviceListViewModel = viewModel(),
    navToDeviceDetails: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DeviceList") },
                navigationIcon = { IconButton(onClick = {}) { Image(vectorResource(id = R.drawable.ic_launcher_foreground)) } }
            )
        }
    ) {
        val devices = viewModel.devices
        LazyColumn {
            items(devices) { device ->
                ListItem(Modifier.clickable { navToDeviceDetails(device.address) }) {
                    Text(device.name)
                }
            }
        }
    }
}