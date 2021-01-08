package com.noahjutz.findchip.ui.device_details

import android.app.Application
import android.bluetooth.*
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noahjutz.findchip.util.hexStringToByteArray
import kotlinx.coroutines.delay
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

        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            super.onReadRemoteRssi(gatt, rssi, status)
            _rssi.value = rssi
        }
    }

    private fun write(value: ByteArray) {
        val serviceUUID = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb")
        val service: BluetoothGattService? = gatt.getService(serviceUUID)

        val characteristicUUID = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb")
        val characteristic = service?.getCharacteristic(characteristicUUID)

        characteristic?.let {
            characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            characteristic.value = value
            gatt.writeCharacteristic(characteristic)
        }
    }

    fun startBeep() {
        write(hexStringToByteArray("a301"))
        _isBeeping.value = true
    }

    fun stopBeep() {
        write(hexStringToByteArray("a401"))
        _isBeeping.value = false
    }

    fun toggleBeep() {
        val payload = if (isBeeping.value) "a401" else "a301"
        write(hexStringToByteArray(payload))
        _isBeeping.value = !isBeeping.value
    }

    private val gatt: BluetoothGatt =
        device.connectGatt(application.applicationContext, true, gattCallback)

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private val _rssi = MutableStateFlow(0)
    val rssi = _rssi.asStateFlow()

    private val _isBeeping = MutableStateFlow(false)
    val isBeeping = _isBeeping.asStateFlow()

    init {
        viewModelScope.launch {
            isConnected.collect {
                if (!it) gatt.connect()
                if (it) gatt.discoverServices()
            }
        }
        viewModelScope.launch {
            while (true) {
                gatt.readRemoteRssi()
                delay(500)
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
