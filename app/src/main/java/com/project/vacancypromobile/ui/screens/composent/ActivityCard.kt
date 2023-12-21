package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.ActivityDetailViewModel
import com.project.vacancypromobile.viewModel.composent.AddToCalendarViewModel
import com.project.vacancypromobile.viewModel.composent.ShowItineraryViewModel
import java.util.Date

@Composable
fun ActivityCard(
    activityDetailViewModel: ActivityDetailViewModel = viewModel(),
    navController: NavController,
    ) {

    val openAlertDialog = remember { mutableStateOf(false) }
    val openItineraryDialog = remember { mutableStateOf(false) }
    val openEditDialog = remember { mutableStateOf(false) }
    OutlinedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Row(Modifier.padding(15.dp)) {
            AsyncImage(
                model = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyAeX0rGP22Zfco3WbT44TFHbKxqmPmIK_s&photo_reference=" + activityDetailViewModel.activityImage,
                placeholder = null,
                error = null,
                contentDescription = "Image du lieux ",
                modifier = Modifier.height(80.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(text = activityDetailViewModel.activityName,)
                Text(text = activityDetailViewModel.activityDescription, color = Color.LightGray)
                Text(
                    text = activityDetailViewModel.activityDate ,
                    color = Color.Gray
                )
                Text(text = activityDetailViewModel.activityPlace, color = Color.Yellow)
            }
            Column(){
                Icon(Icons.Default.DateRange, contentDescription = "Ajouter au calendrier",
                    Modifier
                        .clickable { openAlertDialog.value = true }
                        .size(30.dp))
                Icon(Icons.Default.Place, contentDescription = "Itinéraire" ,
                    Modifier
                        .clickable { openItineraryDialog.value = true }
                        .size(30.dp))
                //Icon(Icons.Default.Edit, contentDescription = "Itinéraire" , Modifier.clickable {  openEditDialog.value = true }.size(30.dp))
                Icon(Icons.Default.Edit, contentDescription = "Itinéraire" ,
                    Modifier
                        .clickable { navController.navigate(Screen.ModifyActivity.route + "/${activityDetailViewModel.activity.id}") }
                        .size(30.dp))
            }
        }

    }
    when {
        openAlertDialog.value ->
        AddToCalendarComp(
            onDismissRequest = { openAlertDialog.value = false },
            addToCalendarViewModel = AddToCalendarViewModel(activityDetailViewModel.activity)
        )
    }
    when {
        openItineraryDialog.value ->
            ShowItineraryComp(
                onDismissRequest = { openItineraryDialog.value = false },
                showItineraryViewModel = ShowItineraryViewModel(activityDetailViewModel.placeName)
            )
    }
    when{
        openEditDialog.value ->
            ShowEditComp(
                onConfirmed = {  },
                onDismissRequest = { openEditDialog.value = false   },
                beginDate = activityDetailViewModel.activityDate,
                endDate = activityDetailViewModel.activityDate
            )


    }
}


@Composable
@Preview
fun ActivityCardPreview() {
    ActivityCard(
        activityDetailViewModel = ActivityDetailViewModel(activity = Activity(1,"name","Ceci est une tres lognue description pour tester mon affichage ",Date(),Date(),
            Place("Ca c'est big cet endroit","","AWU5eFiPT2gyC49KbvhLd7QvNW9aRTZNhciegQsbu0B6A4Ybiu_o_CODKq4rcYeSdAJCxSYk-j74yLa6ck8JL3OzrbMibzERPJ1j1xNhMdu2UG7M2_GuzieoPuyEItExKE-wDSsg0VVSfsHuY1Zho2piZgu-l-qJEuSIBi6hitY0GBCn7BHm")
        )), navController = rememberNavController()
    )
}