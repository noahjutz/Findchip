package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DeviceListViewModel(
    private val bluetoothAdapter: BluetoothAdapter
) : ViewModel() {
    private val _devices = MutableStateFlow(emptyList<BluetoothDevice>())
    val devices = _devices.asStateFlow()
    val namedDevices = devices.map { it.filter { it.name != null } }
    val unnamedDevices = devices.map { it.filter { it.name == null } }

    private val _isScanning = MutableStateFlow(false)
    val isScanning = _isScanning.asStateFlow()

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.device?.let { device ->
                when (callbackType) {
                    ScanSettings.CALLBACK_TYPE_ALL_MATCHES -> if (device !in devices.value) _devices.value += device
                    ScanSettings.CALLBACK_TYPE_MATCH_LOST -> _devices.value -= device
                }
            }
        }
    }

    init {
        scan()
    }

    fun scan() {
        viewModelScope.launch {
            _isScanning.value = true

            bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
            bluetoothAdapter.bluetoothLeScanner.startScan(scanCallback)
            delay(10000)
            bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
        }.invokeOnCompletion { _isScanning.value = false }
    }
}
