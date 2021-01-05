package com.noahjutz.findchip.ui.entry_point

import android.app.Application
import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FindchipAppViewModel(
    private val bluetoothAdapter: BluetoothAdapter,
    private val application: Application
) : ViewModel() {

    val isBluetoothDisabled = flow {
        while (true) {
            emit(!bluetoothAdapter.isEnabled)
            delay(500)
        }
    }

    fun enableBluetooth() = bluetoothAdapter.enable()
}