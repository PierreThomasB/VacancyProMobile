package com.project.vacancypromobile.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Message
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange

class ChatViewModel : ViewModel(){


    val chatTemp by mutableStateOf("")
    val messages by mutableStateOf(listOf<Message>())



    fun sendMessage(){

    }

    private fun setUpPusher(){

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
                Log.i("Pusher", "There was a problem connecting! code ($code), message ($message), exception($e)")
            }
        }, ConnectionState.ALL)

        val channel = pusher.subscribe("my-channel")
        channel.bind("my-event") { event ->
            Log.i("Pusher","Received event with data: $event")
        }
    }
}
