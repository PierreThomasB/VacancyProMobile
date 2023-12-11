package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.composent.PeriodCard
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.HomeViewModel
import com.project.vacancypromobile.viewModel.PeriodViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    runBlocking { homeViewModel.getPeriods() }
    VacancypromobileTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = { Text("Vacancy Pro") }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                Text(text = "Vos Vacances en un clic !")

                if(homeViewModel.periods.isEmpty()){
                    Text(text = "Aucune période n'est disponible")
                }else {
                    for (period in homeViewModel.periods) {
                        PeriodCard(periodViewModel = PeriodViewModel(period))
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