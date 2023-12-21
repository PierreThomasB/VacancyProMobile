package com.project.vacancypromobile.ui.screens

import ActivityPlacesAutocompleteTextField
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
import androidx.compose.material3.SnackbarHost
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
import com.project.vacancypromobile.viewModel.NewActivityViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewActivityScreen(
    newActivityViewModel: NewActivityViewModel = viewModel(),
    navController: NavController,
    periodId: String?
) {
    val newActivityMessage by newActivityViewModel.newActivityMessage.observeAsState()

        val snackbarHostState = remember { SnackbarHostState() }
        if (!newActivityMessage.isNullOrEmpty()) {
            LaunchedEffect(newActivityMessage) {
                snackbarHostState.showSnackbar(message = newActivityMessage!!, duration = SnackbarDuration.Short)
            }
        }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                    title = { Text("Vacancy Pro") })
            },
            bottomBar = { NavBar(navController = navController) }
        ) {innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Ajouter une activité",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newActivityViewModel.activityName,
                        onValueChange = { activityName ->
                            newActivityViewModel.updateActivityName(activityName)
                        },
                        label = {
                            Text("Nom")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        value = newActivityViewModel.activityDescription,
                        onValueChange = { activityDescription ->
                            newActivityViewModel.updateActivityDescription(activityDescription)
                        },
                        label = {
                            Text("Description")
                        }
                    )
                    ActivityPlacesAutocompleteTextField(
                        viewModel = newActivityViewModel,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (newActivityViewModel.activityStartDate != "" && newActivityViewModel.activityEndDate != "") {
                        Text(
                            text = "Date sélectionnée : " + newActivityViewModel.getDatesFormatted(),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    DateRangeComp(
                        modifier = Modifier
                            .fillMaxWidth(),
                        selectStartDate = newActivityViewModel::updateActivityStartDate,
                        selectEndDate = newActivityViewModel::updateActivityEndDate
                    )
                    FilledTonalButton(
                        onClick = {
                            runBlocking {
                                newActivityViewModel.createActivity(periodId)
                            }
                            if(newActivityViewModel.newActivitySuccess) navController.navigate("period_details_screen/$periodId")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Ajouter une activité")
                    }
                }
            }
        }

}


@Composable
@Preview
fun NewActivityScreenPreview() {
    NewActivityScreen(navController = rememberNavController(), periodId = "1")
}