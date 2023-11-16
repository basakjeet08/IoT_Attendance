package iot.lab.qrdetails.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import iot.lab.qrdetails.presentation.screens.CodeScannerScreen
import iot.lab.qrdetails.presentation.screens.SplashScreen
import iot.lab.qrdetails.presentation.viewmodel.CodeScannerViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: CodeScannerViewModel
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
            CodeScannerScreen(viewModel = viewModel)
        }
    }
}