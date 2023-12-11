package com.project.vacancypromobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.vacancypromobile.ui.screens.HomeScreen
import com.project.vacancypromobile.ui.screens.LoginScreen
import com.project.vacancypromobile.ui.screens.NewPeriodScreen
import com.project.vacancypromobile.ui.screens.RegisterScreen
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.HomeViewModel
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import com.project.vacancypromobile.viewModel.RegisterViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    newPeriodViewModel: NewPeriodViewModel,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){HomeScreen(homeViewModel , navController)}
        composable(route = Screen.Login.route){LoginScreen(loginViewModel, navController)}
        composable(route = Screen.Register.route){RegisterScreen(registerViewModel, navController)}
        composable(route = Screen.CreatePeriod.route){ NewPeriodScreen(newPeriodViewModel, navController) }
        composable(route = Screen.Period.route){ NewPeriodScreen(newPeriodViewModel, navController) }

    }
}