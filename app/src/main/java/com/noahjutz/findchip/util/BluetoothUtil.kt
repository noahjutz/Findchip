package com.noahjutz.findchip.util

import android.bluetooth.BluetoothGatt
import java.util.*

fun BluetoothGatt.write(
    value: ByteArray,
    serviceUUID: UUID,
    characteristicUUID: UUID
) {
    getService(serviceUUID)?.getCharacteristic(characteristicUUID)?.let {
        writeCharacteristic(it.also { it.value = value })
    }
}