package com.noahjutz.findchip.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    Box(contentAlignment = Alignment.Center) {
        val isBluetoothEnabled by viewModel.isBluetoothEnabled.collectAsState(initial = false)
        if (isBluetoothEnabled) Text("BT Enabled")
        else Text("BT disabled")
    }
}