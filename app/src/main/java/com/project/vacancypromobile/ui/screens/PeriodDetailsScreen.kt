package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.composent.ActivityCard
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel


@Composable
fun PeriodDetailsScreen(
    periodDetailViewModel: PeriodDetailViewModel = viewModel(),
    navController: NavController = rememberNavController(),

) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val id = backStackEntry.value?.arguments?.getInt("periodId") ?: 0
    periodDetailViewModel.initPeriodDetails(id);
    if(periodDetailViewModel.period == null) {
        //navController.popBackStack()
    }

    Box {
        Column {
            Text("Period Details")

            Text("Name : ${periodDetailViewModel.period?.name}")
            Text("Description : ${periodDetailViewModel.period?.description}")
            Text("Begin Date : ${periodDetailViewModel.period?.beginDate}")
            Text("End Date : ${periodDetailViewModel.period?.endDate}")
        }


        for (activity in periodDetailViewModel.period?.activities ?: emptyList()) {
            ActivityCard()
        }


    }
}




@Composable
@Preview
fun PeriodDetailsScreenPreview() {
   PeriodDetailsScreen()

}