package com.noahjutz.findchip.ui.home

import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HomeViewModel : ViewModel() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val bondedDevices = flow {
        while (true) {
            emit(bluetoothAdapter.bondedDevices.toList())
            delay(500)
        }
    }

    fun scanForDevice(u: String): BluetoothDevice? {
        var device: BluetoothDevice? = null
        for (i in (0..100)) {
            bluetoothAdapter.bluetoothLeScanner.startScan(object : ScanCallback() {
                override fun onScanResult(callbackType: Int, result: ScanResult?) {
                    super.onScanResult(callbackType, result)
                    Log.d("HomeVM", "found ${result?.device}")
                    device = result?.device?.takeIf { it.address == u }?.also {
                        it.connectGatt(null, false, object : BluetoothGattCallback() {
                            override fun onConnectionStateChange(
                                gatt: BluetoothGatt,
                                status: Int,
                                newState: Int
                            ) {
                                when (newState) {
                                    BluetoothProfile.STATE_CONNECTED -> {
                                        Log.i("HomeVM", "Connected to GATT server.")
                                        Log.i(
                                            "HomeVM",
                                            "Attempting to start service discovery: " + gatt.discoverServices()
                                        )
                                    }
                                    BluetoothProfile.STATE_DISCONNECTED -> {
                                        Log.i("HomeVM", "Disconnected from GATT server.")
                                    }
                                }
                            }
                        })
                    }
                }
            })
        }
        return device
    }
}
