package com.noahjutz.findchip

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.onCommit
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
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
            HomeScreen(
                navToDeviceDetails = { address -> navController.navigate("deviceDetails/$address") }
            )
        }
        composable(
            "deviceDetails/{address}",
            arguments = listOf(navArgument("address") { type = NavType.StringType })
        ) { backStackEntry ->
            val address = backStackEntry.arguments!!.getString("address")
            Text("Address: $address")
        }
    }

    val isBluetoothEnabled by viewModel.isBluetoothEnabled.collectAsState(initial = false)
    onCommit(isBluetoothEnabled) {
        navController.popBackStack(navController.graph.startDestination, false)
        if (isBluetoothEnabled) navController.navigate("home")
        else navController.navigate("bluetoothDisabledAlert")
    }
}
