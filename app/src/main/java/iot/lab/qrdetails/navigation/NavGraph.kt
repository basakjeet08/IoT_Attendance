package iot.lab.qrdetails.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import iot.lab.qrdetails.screens.HomeScreen
import iot.lab.qrdetails.screens.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Splash.route
    ) {

        // Splash Screen
        composable(route = NavigationRoutes.Splash.route) {
            SplashScreen {
                navController.navigate(NavigationRoutes.Home.route) {
                    popUpTo(NavigationRoutes.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        // Home Screen
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen()
        }
    }
}