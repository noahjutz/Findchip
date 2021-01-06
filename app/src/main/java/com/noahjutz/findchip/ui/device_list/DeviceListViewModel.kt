package com.noahjutz.findchip.ui.device_list

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.ViewModel

class DeviceListViewModel : ViewModel() {
    val devices = emptyList<BluetoothDevice>()
}