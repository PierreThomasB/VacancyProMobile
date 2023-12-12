package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import java.util.Date
import javax.inject.Inject

class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository) : ViewModel() {


    val period by mutableStateOf<Period>(Period(1,"name","description", Date(),Date() , Place(name="",id="","") , emptyList()))

    val periodName by mutableStateOf<String>(period.name)
    val periodDescription by mutableStateOf<String>(period.description)
    val periodBeginDate by mutableStateOf(period.beginDate)
    val periodEndDate by mutableStateOf(period.endDate)




    suspend fun getPeriodDetails(){
        activityRepository.getActivityByPeriod(period.id);
    }




}