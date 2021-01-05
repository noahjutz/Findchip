package com.noahjutz.findchip.ui.entry_point

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FindchipAppViewModel : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val isBluetoothEnabled = flow {
        while (true) {
            emit(bluetoothAdapter.isEnabled)
            delay(500)
        }
    }

    fun enableBluetooth() = bluetoothAdapter.enable()
}