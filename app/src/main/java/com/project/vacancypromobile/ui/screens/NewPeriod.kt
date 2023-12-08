package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPeriodScreen(
    newPeriodViewModel: NewPeriodViewModel =  viewModel()
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
                    OutlinedTextField(
                        value = newPeriodViewModel.periodName,
                        onValueChange = {
                            periodName -> newPeriodViewModel.updatePeriodName(periodName)
                        },
                        label = {
                            Text("Period Name")
                        }
                    )
                    OutlinedTextField(
                        value = newPeriodViewModel.periodDescription,
                        onValueChange = {
                            periodDescription -> newPeriodViewModel.updatePeriodDescription(periodDescription)
                        },
                        label = {
                            Text("Period Description")
                        }
                    )



                    FilledTonalButton(
                        onClick = {
                            runBlocking {
                               // newPeriodViewModel.createPeriod()
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
    NewPeriodScreen()
}