package com.project.vacancypromobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.vacancypromobile.ui.screens.HomeScreen
import com.project.vacancypromobile.ui.screens.LoginScreen
import com.project.vacancypromobile.ui.screens.RegisterScreen
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.RegisterViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){HomeScreen()}
        composable(route = Screen.Login.route){LoginScreen(loginViewModel, navController)}
        composable(route = Screen.Register.route){ RegisterScreen(registerViewModel, navController)}

    }
}