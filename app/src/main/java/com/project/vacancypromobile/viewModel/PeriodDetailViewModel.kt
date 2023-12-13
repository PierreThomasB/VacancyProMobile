package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository , private val id : Int) : ViewModel() {

    init {
        runBlocking {
            _period = getPeriodDetails(id)
        }
    }

    private var _period : Period ;
    val periodName by mutableStateOf<String>(_period.name)
    val periodDescription by mutableStateOf<String>(_period.description)
    val periodBeginDate by mutableStateOf(_period.beginDate)
    val periodEndDate by mutableStateOf(_period.endDate)
    val periodActivity by mutableStateOf<List<Activity>>(_period.activities)




    private suspend fun getPeriodDetails(id : Int) : Period {

        val resp =  activityRepository.getActivityByPeriod(id);
        if(resp != null) {
            return resp;
        }
        return Period(0,"","", Date(),Date(), Place("0","",""), emptyList());
    }




}