package com.project.vacancypromobile.ui.screens

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Login: Screen(route = "login_screen")
    data object Register: Screen(route = "register_screen")
    data object CreatePeriod: Screen(route = "create_period_screen");
    data object Period : Screen(route = "period_screen");
    data object PeriodDetails : Screen(route = "period_details_screen");
}
