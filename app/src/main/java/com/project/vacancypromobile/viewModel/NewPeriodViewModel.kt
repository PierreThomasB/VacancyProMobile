package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewPeriodViewModel  : ViewModel(){


    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")

    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    fun createPeriod() {

          // periodRepository.createPeriod(periodName, periodDescription);
    }

}