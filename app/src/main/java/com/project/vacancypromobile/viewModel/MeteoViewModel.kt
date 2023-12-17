package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Meteo
import dagger.hilt.android.lifecycle.HiltViewModel


class MeteoViewModel  constructor(private val meteo: Meteo) : ViewModel() {

    val ville by mutableStateOf(meteo.ville)
    val temperature by mutableStateOf(meteo.temperature)
    val description by mutableStateOf(meteo.description)
    val urlPhoto by mutableStateOf(meteo.urlPhoto)

}
