package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import javax.inject.Inject

class NewPeriodViewModel @Inject constructor(private val periodRepository: PeriodRepository)  : ViewModel(){


    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")

    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    suspend fun createPeriod() {

        val period = Period(
            name = periodName,
            description = periodDescription);

          periodRepository.createPeriod(period);
    }

}