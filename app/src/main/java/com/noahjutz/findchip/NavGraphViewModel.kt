package com.noahjutz.findchip

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class NavGraphViewModel : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val isBluetoothEnabled = flow {
        while (true) {
            emit(bluetoothAdapter.isEnabled)
            delay(500)
        }
    }
}