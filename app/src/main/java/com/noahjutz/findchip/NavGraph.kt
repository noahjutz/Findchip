package com.noahjutz.findchip

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.noahjutz.findchip.ui.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
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
}
