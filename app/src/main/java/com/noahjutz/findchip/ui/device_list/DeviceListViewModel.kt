package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeviceListViewModel(
    private val bluetoothAdapter: BluetoothAdapter
) : ViewModel() {
    private val _devices = MutableStateFlow(emptyList<BluetoothDevice>())
    val devices = _devices.asStateFlow()

    init {
        bluetoothAdapter.bluetoothLeScanner.startScan(
            listOf(ScanFilter.Builder().build()),
            ScanSettings.Builder().build(),
            object : ScanCallback() {
                override fun onScanResult(callbackType: Int, result: ScanResult?) {
                    super.onScanResult(callbackType, result)
                    Log.i("DeviceListViewModel", "onScanResult: ($callbackType) $result")
                    result?.device?.let { device ->
                        when (callbackType) {
                            ScanSettings.CALLBACK_TYPE_ALL_MATCHES -> {
                                _devices.value += device
                            }
                            ScanSettings.CALLBACK_TYPE_MATCH_LOST -> {
                                _devices.value -= device
                            }
                        }
                    }
                }
            })
    }
}