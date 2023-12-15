package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.project.vacancypromobile.viewModel.MeteoViewModel

@Composable
fun MeteoComp(meteoViewModel: MeteoViewModel = viewModel()) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 50.dp, height = 50.dp)
    ) {
        Column {
            AsyncImage( model = meteoViewModel.urlPhoto,
                placeholder = null,
                error = null,
                contentDescription = "Image de météo ")
            Text("Météo")
            Text(meteoViewModel.ville)
            Text(meteoViewModel.temperature)
            Text(meteoViewModel.description)
        }

    }
}
