package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.MeteoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeteoViewModel @Inject constructor(meteoRepository: MeteoRepository) : ViewModel() {

    val ville by mutableStateOf("Paris")
    val temperature by mutableStateOf("")
    val description by mutableStateOf("")
    val urlPhoto by mutableStateOf("")


    fun getMeteoInfo() {

    }

}
