package com.project.vacancypromobile.ui.screens.composent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.viewModel.PeriodViewModel
import java.util.Date


@Composable
fun PeriodCard(periodViewModel: PeriodViewModel  , onClick: () -> Unit){
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
            .clickable { }
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                Row {
                    AsyncImage(
                        model = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyAeX0rGP22Zfco3WbT44TFHbKxqmPmIK_s&photo_reference="+periodViewModel.urlPhoto,
                        placeholder = null,
                        error = null,
                        contentDescription = "Image du lieux "
                    )
                   Column (Modifier.padding(12.dp)){
                       Text(
                           text = "${periodViewModel.nom}",
                           style = MaterialTheme.typography.displaySmall,
                       )
                       Text(
                           text = "${periodViewModel.description}",
                       )
                       Text(
                           text = "${periodViewModel.beginDate} -> ${periodViewModel.endDate}",
                       )
                   }
                }



            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun LoginScreenPreview() {
    PeriodCard( onClick =  ({}) , periodViewModel = PeriodViewModel(Period(-1 , name = "Ludwig", description = "Salut " , beginDate = Date() , endDate = Date() , Place(id= "-1" , name = "Salut", urlPhoto = "AWU5eFiPT2gyC49KbvhLd7QvNW9aRTZNhciegQsbu0B6A4Ybiu_o_CODKq4rcYeSdAJCxSYk-j74yLa6ck8JL3OzrbMibzERPJ1j1xNhMdu2UG7M2_GuzieoPuyEItExKE-wDSsg0VVSfsHuY1Zho2piZgu-l-qJEuSIBi6hitY0GBCn7BHm" ) , emptyList())))
}