package iot.lab.qrdetails.navigation

sealed class NavigationRoutes(val route: String) {
    object Splash : NavigationRoutes("splash - screen")
    object Home : NavigationRoutes("home - screen")
}
