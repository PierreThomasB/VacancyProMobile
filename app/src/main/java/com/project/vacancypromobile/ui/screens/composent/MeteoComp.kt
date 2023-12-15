package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.project.vacancypromobile.viewModel.MeteoViewModel

@Composable
fun MeteoComp(meteoViewModel: MeteoViewModel = viewModel()) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f)
    ) {
        Column {
            Text("Météo" ,  modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 20.sp)
            AsyncImage( model = meteoViewModel.urlPhoto,
                placeholder = null,
                error = null,
                contentDescription = "Image de météo ",
                modifier = Modifier.size(50.dp)
            )
            Text(meteoViewModel.ville ,  modifier = Modifier.align(Alignment.CenterHorizontally) ,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,)
            Text(meteoViewModel.temperature ,  modifier = Modifier.align(Alignment.CenterHorizontally) ,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,)
            Text(meteoViewModel.description ,  modifier = Modifier.align(Alignment.CenterHorizontally) ,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,)
        }

    }
}
