package com.noahjutz.findchip.ui.home

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HomeViewModel : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val isBluetoothEnabled = flow {
        while (true) {
            emit(bluetoothAdapter.isEnabled)
            delay(500)
        }
    }
}