package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.viewModel.ActivityDetailViewModel
import java.util.Date

@Composable
fun ActivityCard(
    activityDetailViewModel: ActivityDetailViewModel = viewModel(),

    ) {

    Box(modifier =
    Modifier.fillMaxSize().height(100.dp).padding(8.dp),
        )
        Row(Modifier.padding(15.dp)) {
            AsyncImage(
                model = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyAeX0rGP22Zfco3WbT44TFHbKxqmPmIK_s&photo_reference="+activityDetailViewModel.activityImage,
                placeholder = null,
                error = null,
                contentDescription = "Image du lieux ",
                modifier = Modifier.height(80.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp) ) {
                Text(text = activityDetailViewModel.activityName ,  modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = activityDetailViewModel.activityDescription ,  color = Color.LightGray ,  modifier = Modifier.align(Alignment.CenterHorizontally) )
                Text(text = activityDetailViewModel.activityStartDate+" -> "+activityDetailViewModel.activityEndDate ,  color = Color.Gray ,  modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = activityDetailViewModel.activityPlace , color = Color.Yellow ,  modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }

    }


@Composable
@Preview
fun ActivityCardPreview() {
    ActivityCard(activityDetailViewModel = ActivityDetailViewModel(activity = Activity(1,"name","Ceci est une tres lognue description pour tester mon affichage ",Date(),Date(),
        Place("Ca c'est big cet endroit","","AWU5eFiPT2gyC49KbvhLd7QvNW9aRTZNhciegQsbu0B6A4Ybiu_o_CODKq4rcYeSdAJCxSYk-j74yLa6ck8JL3OzrbMibzERPJ1j1xNhMdu2UG7M2_GuzieoPuyEItExKE-wDSsg0VVSfsHuY1Zho2piZgu-l-qJEuSIBi6hitY0GBCn7BHm")
    )))
}