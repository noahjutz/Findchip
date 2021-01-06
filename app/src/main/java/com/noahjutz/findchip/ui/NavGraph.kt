package com.noahjutz.findchip.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noahjutz.findchip.ui.device_details.DeviceDetails
import com.noahjutz.findchip.ui.device_list.DeviceList
import com.noahjutz.findchip.ui.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "deviceList") {
        composable("home") {
            HomeScreen()
        }

        composable("deviceList") {
            DeviceList()
        }

        composable("deviceDetails") {
            DeviceDetails(
                popBackStack = { /* TODO */ },
                address = "TODO"
            )
        }
    }
}
