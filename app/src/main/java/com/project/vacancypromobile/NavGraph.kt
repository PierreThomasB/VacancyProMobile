package com.project.vacancypromobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.vacancypromobile.ui.screens.HomeScreen
import com.project.vacancypromobile.ui.screens.LoginScreen
import com.project.vacancypromobile.ui.screens.NewPeriodScreen
import com.project.vacancypromobile.ui.screens.Screen

@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Home.route){HomeScreen()}
        composable(route = Screen.Login.route){LoginScreen()}
        composable(route = Screen.CreatePeriod.route){NewPeriodScreen()}


    }
}