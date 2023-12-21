package com.project.vacancypromobile.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.composent.NavBar
import com.project.vacancypromobile.ui.screens.composent.PeriodCard
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.HomeViewModel
import com.project.vacancypromobile.viewModel.PeriodViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    homeViewModel.loadCurrentUser()
    val activity = (LocalContext.current as? Activity)
    if(homeViewModel.getUser().username != null) {
        runBlocking { homeViewModel.getPeriods() }
    }
    VacancypromobileTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                    title = { Text("Vacancy Pro") })
            },
            bottomBar = { NavBar(navController = navController) }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                Text(text = "Vos Vacances en un clic !" , modifier = Modifier.align(Alignment.CenterHorizontally) , fontSize = 25.sp)

                if (homeViewModel.periods.isEmpty()) {
                    Text(text = "Aucune période n'est disponible")
                } else {
                    Column( Modifier.verticalScroll(rememberScrollState())) {
                    for (period in homeViewModel.periods) {
                        PeriodCard(periodViewModel = PeriodViewModel(period) ,
                                onClick = {
                                    Log.d("Period", "Period clicked + ${period.id}")
                                    navController.navigate("period_details_screen/"+period.id)
                                })
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                    ) {
                        Button(onClick = { homeViewModel.logout(activity)  }) {
                            Row {
                                Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                                Text(text = "Logout")
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}