package com.project.vacancypromobile.ui.screens

const val ACTIVITY_ROUTE = "activity"
const val HOME_ROUTE = "home"
sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Login: Screen(route = "login_screen")
    data object Register: Screen(route = "register_screen")
    data object CreatePeriod: Screen(route = "create_period_screen");

    data object NewActivity: Screen(route = "new_activity_screen/{periodId}");
    data object Period : Screen(route = "period_screen");
    data object PeriodDetails : Screen(route = "period_details_screen/{periodId}");

}
