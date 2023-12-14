package com.project.vacancypromobile.ui.screens

import PlacesAutocompleteTextField
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.composent.DateRangeComp
import com.project.vacancypromobile.ui.screens.composent.NavBar
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import kotlinx.coroutines.runBlocking



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPeriodScreen(
    newPeriodViewModel: NewPeriodViewModel = viewModel(),
    navController: NavController
) {





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
        ){ innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ){

                Column {
                    Text(text = "Create a new period" ,  modifier = Modifier.align(Alignment.CenterHorizontally))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newPeriodViewModel.periodName,
                        onValueChange = {
                            periodName -> newPeriodViewModel.updatePeriodName(periodName)
                        },
                        label = {
                            Text("Period Name")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        value = newPeriodViewModel.periodDescription,
                        onValueChange = {
                            periodDescription -> newPeriodViewModel.updatePeriodDescription(periodDescription)
                        },
                        label = {
                            Text("Period Description")
                        }
                    )

                    PlacesAutocompleteTextField( modifier = Modifier
                        .fillMaxWidth()
                        )
                    if(newPeriodViewModel.periodStartDate != "" && newPeriodViewModel.periodEndDate != "") {
                        Text(
                            text = "Date sélectionnée : " + newPeriodViewModel.periodStartDate + " -> " + newPeriodViewModel.periodEndDate,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    DateRangeComp( modifier = Modifier
                        .fillMaxWidth() , selectStartDate = newPeriodViewModel::updatePeriodStartDate , selectEndDate = newPeriodViewModel::updatePeriodEndDate
                    )
                    FilledTonalButton(
                        onClick = {
                            runBlocking {
                                newPeriodViewModel.createPeriod()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)


                    ) {
                        Text("Create Period")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun NewPeriodScreenPreview() {
    NewPeriodScreen(navController = rememberNavController() )
}