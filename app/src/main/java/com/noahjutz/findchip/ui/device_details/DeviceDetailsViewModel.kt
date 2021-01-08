package com.noahjutz.findchip.ui.device_details

import android.app.Application
import android.bluetooth.*
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noahjutz.findchip.util.hexStringToByteArray
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class DeviceDetailsViewModel(
    device: BluetoothDevice,
    application: Application,
) : ViewModel() {
    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            _isConnected.value = newState == BluetoothProfile.STATE_CONNECTED
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            Log.d(
                TAG,
                "onServicesDiscovered: ${gatt?.services?.map { it.uuid }} ${gatt?.services?.map { it.characteristics.map { it.uuid } }}"
            )
        }
    }

    fun write() {
        val serviceUUID = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb")
        val service: BluetoothGattService? = gatt.getService(serviceUUID)

        val characteristicUUID = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb")
        val characteristic = service?.getCharacteristic(characteristicUUID)

        characteristic?.let {
            characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            characteristic.value = hexStringToByteArray("a301")
                .also { Log.d(TAG, "byteArray: ${it.joinToString(",")}") }
            gatt.writeCharacteristic(characteristic)
        }
    }

    private val gatt: BluetoothGatt =
        device.connectGatt(application.applicationContext, true, gattCallback)

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    init {
        viewModelScope.launch {
            isConnected.collect {
                if (!it) gatt.connect()
                if (it) gatt.discoverServices()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("DeviceDetailsVM", "onCleared")
        gatt.disconnect()
    }

    companion object {
        private const val TAG = "DeviceDetailsViewModel"
    }
}
