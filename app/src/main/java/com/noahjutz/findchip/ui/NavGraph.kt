package com.noahjutz.findchip.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noahjutz.findchip.ui.device_details.DeviceDetails
import com.noahjutz.findchip.ui.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "deviceDetails") {
        composable("home") {
            HomeScreen()
        }

        composable("deviceDetails") {
            DeviceDetails(
                popBackStack = { /* TODO */ },
                address = "TODO"
            )
        }
    }
}
