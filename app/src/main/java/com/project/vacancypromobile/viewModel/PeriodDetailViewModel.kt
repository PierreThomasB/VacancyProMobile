package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository , private val periodRepository: PeriodRepository )  : ViewModel() {



    fun initPeriodDetails(id : Int) {
        if(id != 0 ) {
            runBlocking {
                getPeriodDetails(id)
            }
        }
    }


    private var activities by mutableStateOf(emptyList<Activity>())

     var period by mutableStateOf(Period(0,"","", Date(),Date(), Place("0","",""), emptyList()))

    



    private suspend fun getPeriodDetails(id : Int)  {
        period = periodRepository.getPeriod(id)!!;
        val resp =  activityRepository.getActivitiesByPeriod(id)
        if(resp != null) {
            activities = resp
        }
    }




}