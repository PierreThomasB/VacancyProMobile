package com.project.vacancypromobile.viewModel.composent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking


class ShowEditViewModel : ViewModel() {


    private var periodStartDate by mutableStateOf<String>("")
    private var periodEndDate by mutableStateOf<String>("")


    fun updatePeriodStartDate(periodStartDate: String) {
        //Log.d("Testing ", periodStartDate)
        this.periodStartDate = periodStartDate
    }

    fun updatePeriodEndDate(periodEndDate: String) {
        //Log.d("Testing ", periodEndDate)
        this.periodEndDate = periodEndDate
    }

     fun editActivity() {
        runBlocking {
            //activityRepository.editActivity()
        }
        //activityRepository.editActivity()
    }


}