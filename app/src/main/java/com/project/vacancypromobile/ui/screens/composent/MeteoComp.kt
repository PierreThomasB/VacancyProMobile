package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.project.vacancypromobile.viewModel.MeteoViewModel

@Composable
fun meteoComp(meteoViewModel: MeteoViewModel = viewModel()) {

    Box() {
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
