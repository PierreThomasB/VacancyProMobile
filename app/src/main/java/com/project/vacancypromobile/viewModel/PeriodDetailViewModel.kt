package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.models.Period
import javax.inject.Inject

class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository, private val period:Period) : ViewModel() {


    val periodName by mutableStateOf<String>(period.name)
    val periodDescription by mutableStateOf<String>(period.description)
    val periodBeginDate by mutableStateOf(period.beginDate)
    val periodEndDate by mutableStateOf(period.endDate)




    suspend fun getPeriodDetails(){
        activityRepository.getActivityByPeriod(period.id);
    }




}