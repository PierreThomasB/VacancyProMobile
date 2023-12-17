package com.project.vacancypromobile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.vacancypromobile.ui.screens.HomeScreen
import com.project.vacancypromobile.ui.screens.LoginScreen
import com.project.vacancypromobile.ui.screens.NewActivityScreen
import com.project.vacancypromobile.ui.screens.NewPeriodScreen
import com.project.vacancypromobile.ui.screens.PeriodDetailsScreen
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.HomeViewModel
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.NewActivityViewModel
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    newPeriodViewModel: NewPeriodViewModel,
    newActivityViewModel: NewActivityViewModel,
    homeViewModel: HomeViewModel,
    periodDetailViewModel: PeriodDetailViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) { HomeScreen(homeViewModel, navController) }
        composable(route = Screen.Login.route) { LoginScreen(loginViewModel, navController) }
        composable(route = Screen.CreatePeriod.route) {
            NewPeriodScreen(
                newPeriodViewModel,
                navController
            )
        }
        composable(route = Screen.Period.route) {
            NewPeriodScreen(
                newPeriodViewModel,
                navController
            )
        }
        composable(
            route = Screen.PeriodDetails.route,
            arguments = listOf(navArgument("periodId") { type = NavType.IntType })
        ) { PeriodDetailsScreen(periodDetailViewModel, navController) }

        composable(Screen.NewActivity.route+"/{periodId}") { backStackEntry ->
            val periodId = backStackEntry.arguments?.getString("periodId")
            NewActivityScreen(
                newActivityViewModel,
                navController,
                periodId
            )
        }
        /*navigation(
            route = ACTIVITY_ROUTE,
            startDestination = Screen.PeriodDetails.route
        ) {
            composable(route = Screen.NewActivity.route, arguments = listOf(navArgument("periodId") { type = NavType.IntType })) {
                NewActivityScreen(
                    newActivityViewModel,
                    navController
                )
            }
        }*/
    }
}
