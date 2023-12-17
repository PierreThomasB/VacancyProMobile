package com.project.vacancypromobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.places.api.net.PlacesClient
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.HomeViewModel
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.MainViewModel
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel
import com.project.vacancypromobile.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val newPeriodViewModel: NewPeriodViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val periodDetailViewModel: PeriodDetailViewModel by viewModels()

    private lateinit var navController: NavHostController
    private lateinit var placesClient : PlacesClient;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            SetupNavGraph(
                navController = navController,
                loginViewModel = loginViewModel,
                registerViewModel = registerViewModel,
                newPeriodViewModel = newPeriodViewModel,
                homeViewModel =  homeViewModel,
                periodDetailViewModel = periodDetailViewModel,
            )
            mainViewModel.loadUser()
            val user = mainViewModel.getCurrentUser()
            if (user == null) navController.navigate(route = Screen.Login.route) else navController.navigate(route = Screen.Home.route)
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VacancypromobileTheme {
        Greeting("Android")
    }
}*/
