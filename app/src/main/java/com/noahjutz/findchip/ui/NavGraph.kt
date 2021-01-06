package com.noahjutz.findchip.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.noahjutz.findchip.ui.device_details.DeviceDetails
import com.noahjutz.findchip.ui.device_list.DeviceList

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "deviceList") {
        composable("deviceList") {
            DeviceList(
                navToDeviceDetails = { navController.navigate("deviceDetails/$it") }
            )
        }

        composable("deviceDetails/{address}") { backStackEntry ->
            DeviceDetails(
                popBackStack = { navController.popBackStack() },
                address = backStackEntry.arguments!!.getString("address")!!
            )
        }
    }
}
