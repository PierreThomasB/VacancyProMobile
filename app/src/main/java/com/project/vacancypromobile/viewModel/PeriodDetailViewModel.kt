package com.project.vacancypromobile.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.datas.ChatRepository
import com.project.vacancypromobile.datas.MeteoRepository
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.models.Meteo
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.requests.ChatReceiveRequest
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PeriodDetailViewModel @Inject constructor(private val activityRepository: ActivityRepository , private val periodRepository: PeriodRepository  , private val meteoRepository: MeteoRepository , private val chatRepository: ChatRepository , private val userRepository: UserRepository)  : ViewModel() {

    fun init(id : Int) {
        if(id != 0 ) {
            runBlocking {
                getPeriodDetails(id)
                //getMeteoInfos()
                getMessages()
            }
            initReadingMessage()
        }
    }

    private fun initReadingMessage() {
        val options = PusherOptions()
        options.setCluster("eu");
        val pusher = Pusher("74f1716b51dbbc6c19ca", options)
        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.i("Pusher", "State changed from ${change.previousState} to ${change.currentState}")
            }
            override fun onError(
                message: String,
                code: String,
                e: Exception
            ) {
            }
        }, ConnectionState.ALL)
        val channel = pusher.subscribe("channel_"+period.id)
        channel.bind("my-event") { event ->
            val data = Gson().fromJson(event.data , ChatReceiveRequest::class.java);
            val message = Message(id = data.id , message = data.message , channel = data.channel , userName = "Pipi Thomas" , date = data.date )
            if(chatRepository.addReceivedMessage(message)) {
                messages = messages + message
            }
            Log.i("Pusher","Received event with data: $message")
        }
    }
    private val _addUserMessage = MutableLiveData<String>()
    val addUserMessage: MutableLiveData<String> get() = _addUserMessage
    var users by mutableStateOf(emptyMap<String, User>())
    var userIdToAdd by mutableStateOf("")
    var meteo by mutableStateOf(Meteo("","","",""))
    var activities by mutableStateOf(emptyList<Activity>())
    var period by mutableStateOf(Period(0,"","", Date(),Date(), Place("0","",""), emptyList()))
    var messages by mutableStateOf(emptyList<Message>())
    var tempMessage by mutableStateOf("")


    fun updateTempMessage(temp : String ){
        tempMessage = temp;
    }

    fun orderActivitiesByDate() {
        activities = activities.sortedBy { it.beginDate }
    }

    fun orderActivitiesByDateDesc() {
        activities = activities.sortedByDescending { it.beginDate }
    }

    fun sendMessage(){
        runBlocking {
            val user = userRepository.getCurrentUser();
            if(user != null) {
                chatRepository.sendMessage(tempMessage, user)
                tempMessage = ""
            }
        }
    }


    private suspend fun getPeriodDetails(id : Int)  {
        period = periodRepository.getPeriod(id)!!;
        users = userRepository.loadUsers(id)
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

    fun updateUserToAdd(userId: String) {
        this.userIdToAdd = userId
    }

    suspend fun addUserToPeriod() {
        if (userIdToAdd.isNotEmpty()) {
            periodRepository.addUserToPeriod(userIdToAdd, period.id)
            /*if (resp) {
                _addUserMessage.value = "Utilisateur ajouté"
            } else {
                _addUserMessage.value = "Utilisateur non ajouté"
            }*/
        }

    }


}