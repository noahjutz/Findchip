package com.noahjutz.findchip.ui.entry_point

import android.Manifest
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
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

    val isLocationPermissionMissing = flow {
        while (true) {
            emit(
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        ContextCompat.checkSelfPermission(
                            application.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        != PackageManager.PERMISSION_GRANTED
            )
            delay(500)
        }
    }

    fun enableBluetooth() = bluetoothAdapter.enable()
}