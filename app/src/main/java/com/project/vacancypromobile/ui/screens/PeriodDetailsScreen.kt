package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel
import java.util.Date


@Composable
fun PeriodDetailsScreen(
    navController: NavController = rememberNavController(),
    periodId: Int = 1,
    periodDetailViewModel: PeriodDetailViewModel
) {
    Box(){
        Text("Period Details")

    }

}


@Composable
@Preview
fun PeriodDetailsScreenPreview() {
    PeriodDetailsScreen(periodDetailViewModel = PeriodDetailViewModel(Period(1,"name","description",
        Date(),Date() , Place("1","name","description")
    )))
}