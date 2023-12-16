package com.project.vacancypromobile.ui.screens

import PlacesAutocompleteTextField
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPeriodScreen(
    newPeriodViewModel: NewPeriodViewModel = viewModel(),
    navController: NavController
) {
    val newPeriodMessage by newPeriodViewModel.newPeriodMessage.observeAsState()
    VacancypromobileTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        if (!newPeriodMessage.isNullOrEmpty()) {
            LaunchedEffect(newPeriodMessage) {
                snackbarHostState.showSnackbar(message = newPeriodMessage!!, duration = SnackbarDuration.Short)
            }
        }
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Ajouter une période de vacances",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newPeriodViewModel.periodName,
                        onValueChange = { periodName ->
                            newPeriodViewModel.updatePeriodName(periodName)
                        },
                        label = {
                            Text("Nom")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        value = newPeriodViewModel.periodDescription,
                        onValueChange = { periodDescription ->
                            newPeriodViewModel.updatePeriodDescription(periodDescription)
                        },
                        label = {
                            Text("Description")
                        }
                    )
                    PlacesAutocompleteTextField(
                        newPeriodViewModel,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    if (newPeriodViewModel.periodStartDate != "" && newPeriodViewModel.periodEndDate != "") {
                        Text(
                            text = "Date sélectionnée : " + newPeriodViewModel.periodStartDate + " -> " + newPeriodViewModel.periodEndDate,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    DateRangeComp(
                        modifier = Modifier
                            .fillMaxWidth(),
                        selectStartDate = newPeriodViewModel::updatePeriodStartDate,
                        selectEndDate = newPeriodViewModel::updatePeriodEndDate
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
                        Text("Ajouter une période")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun NewPeriodScreenPreview() {
    NewPeriodScreen(navController = rememberNavController())
}