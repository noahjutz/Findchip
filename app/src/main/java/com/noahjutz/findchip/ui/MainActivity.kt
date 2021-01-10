package com.noahjutz.findchip.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.platform.setContent
import androidx.core.app.ActivityCompat
import com.noahjutz.findchip.ui.entry_point.FindchipApp


class MainActivity : AppCompatActivity() {
    private val requestLocationPermission = {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
        )
    }

    private fun openUrl(url: String) =
        startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(url) })

    private fun enableLocation() =
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Scaffold {
                    FindchipApp(
                        requestLocationPermission = requestLocationPermission,
                        openUrl = ::openUrl,
                        enableLocation = ::enableLocation
                    )
                }
            }
        }

        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE not supported", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
