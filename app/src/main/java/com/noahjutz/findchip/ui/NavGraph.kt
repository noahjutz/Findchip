package com.noahjutz.findchip.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.noahjutz.findchip.ui.about.AboutApp
import com.noahjutz.findchip.ui.device_details.DeviceDetails
import com.noahjutz.findchip.ui.device_list.DeviceList

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "deviceList") {
        composable("deviceList") {
            DeviceList(
                navToDeviceDetails = { device ->
                    navController.currentBackStackEntry?.arguments?.putParcelable(
                        "bt_device", device
                    )
                    navController.navigate("deviceDetails")
                },
                navToAboutApp = { navController.navigate("aboutApp") }
            )
        }

        composable(
            "deviceDetails",
        ) {
            DeviceDetails(
                popBackStack = { navController.popBackStack() },
                device = navController.previousBackStackEntry?.arguments?.getParcelable("bt_device")!!,
            )
        }

        composable("aboutApp") {
            AboutApp(popBackStack = { navController.popBackStack() })
        }
    }
}
