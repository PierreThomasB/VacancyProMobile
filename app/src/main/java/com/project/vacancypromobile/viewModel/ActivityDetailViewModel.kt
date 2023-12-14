package com.project.vacancypromobile.viewModel

import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Activity

class ActivityDetailViewModel (activity : Activity) : ViewModel(){

    val activityName = activity.name
    val activityDescription = activity.description
    val activityStartDate = activity.startDate.toString()
    val activityEndDate = activity.endDate.toString()
    val activityPlace = activity.place.name
    val activityImage = activity.place.urlPhoto





}