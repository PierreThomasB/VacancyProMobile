package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val periodRepository: PeriodRepository
) : ViewModel(){

    var periods by mutableStateOf(emptyList<Period>());

    suspend fun getPeriods() {
        periods = periodRepository.getAllPeriod()
    }







}