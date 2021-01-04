package com.noahjutz.findchip.ui.bluetooth_disabled_alert

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class BluetoothDisabledAlertViewModel : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    fun enableBluetooth() = bluetoothAdapter.enable()
}