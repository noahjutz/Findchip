package com.noahjutz.findchip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.onCommit
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.noahjutz.findchip.ui.bluetooth_disabled_alert.BluetoothDisabledAlert
import com.noahjutz.findchip.ui.home.HomeScreen

@Composable
fun NavGraph(
    viewModel: NavGraphViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("bluetoothDisabledAlert") {
            BluetoothDisabledAlert()
        }

        composable("home") {
            HomeScreen()
        }
    }

    val isBluetoothEnabled by viewModel.isBluetoothEnabled.collectAsState(initial = false)
    onCommit(isBluetoothEnabled) {
        navController.popBackStack(navController.graph.startDestination, false)
        if (isBluetoothEnabled) navController.navigate("home")
        else navController.navigate("bluetoothDisabledAlert")
    }
}
