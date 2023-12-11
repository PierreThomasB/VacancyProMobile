package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.ui.screens.composent.DateRangeComp
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.NewActivityViewModel
import kotlinx.coroutines.runBlocking

@Composable
public fun NewActivity(
    newActivityViewModel: NewActivityViewModel = viewModel(),
) {
    VacancypromobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column {
                    Text(text = "Create a new activity" ,  modifier = Modifier.align(Alignment.CenterHorizontally))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newActivityViewModel.activityName,
                        onValueChange = {
                                activityName -> newActivityViewModel.updateActivityName(activityName)
                        },
                        label = {
                            Text("Period Name")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        value = newActivityViewModel.activityDescription,
                        onValueChange = {
                                activityDescription -> newActivityViewModel.updateActivityDescription(activityDescription)
                        },
                        label = {
                            Text("Period Description")
                        }
                    )
                    DateRangeComp( modifier = Modifier
                        .fillMaxWidth() , selectStartDate = newActivityViewModel::updateActivityStartDate , selectEndDate = newActivityViewModel::updateActivityEndDate
                    )
                    FilledTonalButton(
                        onClick = {
                            runBlocking {
                                newActivityViewModel.createActivity()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)


                    ) {
                        Text("Create Activity")
                    }
                }
        }
            }

        }


    }


@Composable
@Preview
fun NewActivityScreenPreview() {
    NewActivity()
}