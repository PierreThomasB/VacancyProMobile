package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.datas.ChatRepository
import com.project.vacancypromobile.datas.MeteoRepository
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.models.Meteo
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository , private val periodRepository: PeriodRepository  , private val meteoRepository: MeteoRepository , private val chatRepository: ChatRepository)  : ViewModel() {

    fun init(id : Int) {
        if(id != 0 ) {
            runBlocking {
                getPeriodDetails(id)
                getMeteoInfos()
                getMessages()
            }
        }
    }

    var meteo by mutableStateOf(Meteo("","","",""))
    var activities by mutableStateOf(emptyList<Activity>())
    var period by mutableStateOf(Period(0,"","", Date(),Date(), Place("0","",""), emptyList()))
    var messages by mutableStateOf(emptyList<Message>())
    var tempMessage by mutableStateOf("")


    fun updateTempMessage(temp : String ){
        tempMessage = temp;
    }


    private suspend fun getPeriodDetails(id : Int)  {
        period = periodRepository.getPeriod(id)!!;
        val resp =  activityRepository.getActivitiesByPeriod(id)
        if(resp != null) {
            activities = resp
        }
    }

    private suspend fun getMeteoInfos() {
        val resp = meteoRepository.getMeteoInfo(period.place.name)
        if (resp != null) {
            meteo = resp

        }
    }

    private suspend fun getMessages() {
        val resp = chatRepository.getAllMessages("channel_"+period.id)
        if (resp != null) {
            messages = resp

        }
    }



}