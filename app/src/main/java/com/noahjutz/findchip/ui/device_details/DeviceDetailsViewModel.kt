package com.noahjutz.findchip.ui.device_details

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothProfile
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeviceDetailsViewModel(
    device: BluetoothDevice,
    application: Application,
) : ViewModel() {
    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            _isConnected.value = newState == BluetoothProfile.STATE_CONNECTED
        }
    }

    private val gatt: BluetoothGatt =
        device.connectGatt(application.applicationContext, true, gattCallback).also { it.connect() }

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        Log.d("DeviceDetailsVM", "onCleared")
        gatt.disconnect()
    }
}