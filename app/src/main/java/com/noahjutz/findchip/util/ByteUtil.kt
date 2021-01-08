package com.noahjutz.findchip.util

fun hexStringToByteArray(hex: String): ByteArray {
    return ByteArray(hex.length / 2).also {
        for (i in hex.indices step 2) {
            val n1 = Character.digit(hex[i], 16) shl 4
            val n2 = Character.digit(hex[i + 1], 16)
            val byte = (n1 + n2).toByte()
            it[i / 2] = byte
        }
    }
}
