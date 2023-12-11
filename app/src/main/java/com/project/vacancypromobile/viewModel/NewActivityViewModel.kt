package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewActivityViewModel : ViewModel(){
    var activityName  by mutableStateOf<String>("");
    var activityDescription  by mutableStateOf<String>("");
    var activityStartDate  by mutableStateOf<String>("");
    var activityEndDate  by mutableStateOf<String>("");


    fun updateActivityName(activityName: String) {
        this.activityName = activityName
    }
    fun updateActivityDescription(activityDescription: String) {
        this.activityDescription = activityDescription
    }
    fun updateActivityStartDate(activityStartDate: String) {
        this.activityStartDate = activityStartDate
    }
    fun updateActivityEndDate(activityEndDate: String) {
        this.activityEndDate = activityEndDate
    }

    suspend fun createActivity() {
        //val activity = Activity(name = activityName, description = activityDescription, startDate = activityStartDate, endDate = activityEndDate);
        //activityRepository.createActivity(activity);
    }



}