package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class ChatRepository  @Inject constructor(private  val api: ApiService): Serializable {


    suspend fun getAllMessages(channel: String) : List<Message>{
       val resp =  api.getAllMessages(channel)
        if(resp.isSuccessful && resp.body() != null) {
            val response = resp.body();
            val messages = mutableListOf<Message>();
            for(message in response!!) {
               //messages.add(Message(-1,"message","message.content",message.user,message.date))
            }
            return messages;
        }
        else {
            val error = resp.errorBody()
        }
        return emptyList();
    }
}