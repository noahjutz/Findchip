package com.noahjutz.findchip.ui

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class NavGraphViewModel(
    private val application: Application
) : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val isBluetoothEnabled = flow {
        while (true) {
            emit(bluetoothAdapter.isEnabled)
            delay(500)
        }
    }
}