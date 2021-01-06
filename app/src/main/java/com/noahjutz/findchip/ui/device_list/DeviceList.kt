package com.noahjutz.findchip.ui.device_list

import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.viewinterop.viewModel
import com.noahjutz.findchip.R

@Composable
fun DeviceList(
    viewModel: DeviceListViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DeviceList") },
                navigationIcon = { IconButton(onClick = {}) { Image(vectorResource(id = R.drawable.ic_launcher_foreground)) } }
            )
        }
    ) {
        Text("TODO Device list layout")
    }
}