package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Activity

class ActivityDetailViewModel (activity : Activity) : ViewModel(){

    val activity by mutableStateOf( activity)
    val activityName by mutableStateOf( activity.name)
    val activityDescription by mutableStateOf( activity.description)
    val activityDate by mutableStateOf( activity.afficherDate())
    val activityPlace by mutableStateOf( activity.place.name)
    val activityImage by mutableStateOf( activity.place.urlPhoto)





}