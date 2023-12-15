package com.project.vacancypromobile.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.composent.ActivityCard
import com.project.vacancypromobile.viewModel.ActivityDetailViewModel
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel
import kotlinx.coroutines.runBlocking


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodDetailsScreen(
    periodDetailViewModel: PeriodDetailViewModel = viewModel(),
    navController: NavController = rememberNavController(),

) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    runBlocking {
        periodDetailViewModel.init(backStackEntry.value?.arguments?.getInt("periodId") ?: 0);
    }

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = { Text(periodDetailViewModel.period.name , fontWeight = FontWeight.Bold ,  fontSize = 30.sp) })
        },

        bottomBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row() {
                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )

                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Add")
                        }
                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )

                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Rounded.MailOutline, contentDescription = "Add")
            }
        }
    ){
        Column (Modifier.padding(it)){
            Column( Modifier.align(alignment = Alignment.Start)) {
                Text(" ${periodDetailViewModel.period?.description}" , modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(" ${periodDetailViewModel.period?.afficherDate()}", modifier = Modifier.align(Alignment.CenterHorizontally))

            }
            Column( Modifier.align(alignment = Alignment.End)) {
               //MeteoComp( meteoViewModel = MeteoViewModel( periodDetailViewModel.meteo))
            }
            Text("Activities : "  ,modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally) , fontSize = 20.sp)
            for (activity in periodDetailViewModel.activities ) {
                ActivityCard(activityDetailViewModel = ActivityDetailViewModel(activity))
            }
        }


    }

}




@Composable
@Preview
fun PeriodDetailsScreenPreview() {
   PeriodDetailsScreen()

}