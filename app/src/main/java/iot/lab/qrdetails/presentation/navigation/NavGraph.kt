package iot.lab.qrdetails.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import iot.lab.qrdetails.presentation.screens.CodeScannerScreenControl
import iot.lab.qrdetails.presentation.screens.RegistrationResultScreen
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
                navController.navigate(NavigationRoutes.Scanner.route) {
                    popUpTo(NavigationRoutes.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        // Home Screen
        composable(route = NavigationRoutes.Scanner.route) {

            val scannerStates by viewModel.scannerStates.collectAsState()
            val registrationState by viewModel.registrationApiState.collectAsState()

            CodeScannerScreenControl(
                scannerState = scannerStates,
                registrationState = registrationState,
                onStartScannerClick = { viewModel.startScanner() },
                resetToIdleState = { viewModel.resetScannerState() }
            )
        }

        composable(route = NavigationRoutes.RegistrationResults.route) {
            RegistrationResultScreen()
        }
    }
}