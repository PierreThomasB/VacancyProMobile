package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class ChatRepository  @Inject constructor(private  val api: ApiService): Serializable {


    suspend fun getAllMessages(channel: String) : List<Message>{
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