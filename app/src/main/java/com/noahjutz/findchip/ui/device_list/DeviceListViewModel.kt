package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeviceListViewModel(
    private val bluetoothAdapter: BluetoothAdapter
) : ViewModel() {
    private val _devices = MutableStateFlow(emptyList<BluetoothDevice>())
    val devices = _devices.asStateFlow()

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

    private val scanFilters = listOf(ScanFilter.Builder().build()) // TODO

    private val scanSettings = ScanSettings.Builder().build() // TODO

    init {
        scan()
    }

    fun scan() {
        viewModelScope.launch {
            if (_isScanning.value) bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)

            _isScanning.value = true
            bluetoothAdapter.bluetoothLeScanner.startScan(
                scanFilters,
                scanSettings,
                scanCallback,
            )
            delay(5000)
            bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
            _isScanning.value = false
        }
    }
}