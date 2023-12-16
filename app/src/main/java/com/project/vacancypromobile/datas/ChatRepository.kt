package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.ChatRequest
import com.project.vacancypromobile.services.requests.ChatSendRequest
import retrofit2.Response
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class ChatRepository  @Inject constructor(private  val api: ApiService ): Serializable {


    private var currentChannel : String = "null"

    suspend fun sendMessage(message:String , user: User ): Response<ChatRequest> {


            val request = ChatSendRequest(message = message, channel = currentChannel, user = user)
            val response = api.sendMessage(request)
            return response;


    }
    suspend fun getAllMessages(channel: String) : List<Message>{
        currentChannel = channel
       val resp =  api.getAllMessages(channel)
        if(resp.isSuccessful && resp.body() != null) {
            val response = resp.body();
            val messages = mutableListOf<Message>();
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            for(messageRequ in response!!) {
               messages.add(Message(messageRequ.id, messageRequ.message, format.parse(messageRequ.date), messageRequ.user?.userName, messageRequ.channel))
            }
            return messages;
        }
        return emptyList();
    }
}