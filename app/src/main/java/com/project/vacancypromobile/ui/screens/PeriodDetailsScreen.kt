package com.project.vacancypromobile.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.project.vacancypromobile.ui.screens.composent.ChatComp
import com.project.vacancypromobile.ui.screens.composent.MeteoComp
import com.project.vacancypromobile.viewModel.ActivityDetailViewModel
import com.project.vacancypromobile.viewModel.ChatViewModel
import com.project.vacancypromobile.viewModel.MeteoViewModel
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
    var showedChat by remember { mutableStateOf(false) }
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
            FloatingActionButton(onClick = {showedChat = true }) {
                Icon(Icons.Filled.Send, contentDescription = "Add")
            }
        }
    ){
        Column(modifier = Modifier.padding(it)) {
            Text("Infos : "  ,modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally) , fontSize = 20.sp)
            Row (modifier = Modifier.padding(  10.dp)) {
                Column() {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight(0.2f)
                    ) {
                        Text(
                            " ${periodDetailViewModel.period?.description}",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 20.sp
                        )
                        Text(
                            " ${periodDetailViewModel.period?.afficherDate()}",
                            modifier = Modifier.align(Alignment.CenterHorizontally) ,
                            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                        )
                        Text(
                            " ${periodDetailViewModel.period?.place?.name}",
                            modifier = Modifier.align(Alignment.CenterHorizontally) ,
                            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                        )
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    MeteoComp(meteoViewModel = MeteoViewModel(periodDetailViewModel.meteo))
                }
            }
            Text("Activities : "  ,modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally) , fontSize = 20.sp)
            for (activity in periodDetailViewModel.activities) {
                ActivityCard(activityDetailViewModel = ActivityDetailViewModel(activity))
            }
        }

        if(showedChat) {
            ModalBottomSheet(onDismissRequest = { showedChat = false }) {
                Column {
                    Text("Messages : "  ,modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally) , fontSize = 20.sp)
                    Column {
                        for (message in periodDetailViewModel.messages){
                            ChatComp(ChatViewModel(message))
                        }
                        Row( Modifier.padding(8.dp)) {
                            OutlinedTextField(value = periodDetailViewModel.tempMessage, onValueChange = {temp -> periodDetailViewModel.updateTempMessage(temp)} , placeholder = { Text("Message") } , modifier = Modifier.fillMaxWidth(0.8f))
                            Button(onClick = { }) {
                                Icon(Icons.Default.Send, contentDescription = "Envoyer")
                            }
                        }
                    }


                }
            }

        }


    }

}




@Composable
@Preview
fun PeriodDetailsScreenPreview() {
   PeriodDetailsScreen()

}