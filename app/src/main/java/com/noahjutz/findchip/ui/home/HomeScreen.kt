package com.noahjutz.findchip.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToDeviceDetails: (String) -> Unit
) {
    BondedDeviceList(
        viewModel,
        navToDeviceDetails
    )
}

@Composable
fun BondedDeviceList(
    viewModel: HomeViewModel,
    navToDeviceDetails: (String) -> Unit
) {
    val bondedDevices by viewModel.bondedDevices.collectAsState(initial = emptyList())
    LazyColumn {
        items(bondedDevices) { device ->
            ListItem(Modifier.clickable(onClick = {
                navToDeviceDetails(device.address)
            })) {
                Text(device.name)
            }
        }
    }
}
