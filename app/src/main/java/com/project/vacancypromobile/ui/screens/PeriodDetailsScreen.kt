package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun PeriodDetailsScreen(
    periodDetailViewModel: PeriodDetailViewModel = viewModel(),
    navController: NavController = rememberNavController(),

) {
    Box {
        Text("Period Details")

        Text(periodDetailViewModel.periodName , modifier = Modifier.align(Alignment.Center))

        for(activity in periodDetailViewModel.periodActivity){

            }
        }




    }

}


@Composable
@Preview
fun PeriodDetailsScreenPreview() {
   PeriodDetailsScreen()

}