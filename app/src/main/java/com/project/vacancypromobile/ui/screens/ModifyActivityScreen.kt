package com.project.vacancypromobile.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.vacancypromobile.ui.screens.composent.DateRangeComp
import com.project.vacancypromobile.ui.screens.composent.NavBar
import com.project.vacancypromobile.viewModel.EditActivityViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyActivityScreen(
    editActivityViewModel: EditActivityViewModel = viewModel(),
    navController: NavController,
    activityId: Int
) {
    runBlocking { editActivityViewModel.init(activityId) }
    val editActivityMessage by editActivityViewModel.editActivityMessage.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    if (!editActivityMessage.isNullOrEmpty()) {
        LaunchedEffect(editActivityMessage) {
            snackbarHostState.showSnackbar(message = editActivityMessage!!, duration = SnackbarDuration.Short)
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
                    value = editActivityViewModel.activityName,
                    onValueChange = { activityName ->
                        editActivityViewModel.updateActivityName(activityName)
                    },
                    label = {
                        Text("Nom")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    value = editActivityViewModel.activityDescription,
                    onValueChange = { activityDescription ->
                        editActivityViewModel.updateActivityDescription(activityDescription)
                    },
                    label = {
                        Text("Description")
                    }
                )
                if (editActivityViewModel.activityStartDate != "" && editActivityViewModel.activityEndDate != "") {
                    Text(
                        text = "Date sélectionnée : " + editActivityViewModel.getDatesFormatted(),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                DateRangeComp(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selectStartDate = editActivityViewModel::updateActivityStartDate,
                    selectEndDate = editActivityViewModel::updateActivityEndDate,
                )
                FilledTonalButton(
                    onClick = {
                        runBlocking {
                            editActivityViewModel.editActivity(activityId)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Modifier une activité")
                }
            }
        }
    }
}