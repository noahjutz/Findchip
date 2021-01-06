package com.noahjutz.findchip.ui.device_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DeviceList") },
                navigationIcon = { IconButton(onClick = {}) { Image(vectorResource(id = R.drawable.ic_launcher_foreground)) } }
            )
        }
    ) {
        val devices by viewModel.devices.collectAsState()
        LazyColumn {
            items(devices) { device ->
                ListItem(Modifier.clickable { navToDeviceDetails(device.address) }) {
                    Text("This is a device ${device.address} ${device.name}")
                }
            }
        }
    }
}