package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.ChatRequest
import com.project.vacancypromobile.services.requests.ChatSendRequest
import retrofit2.Response
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class ChatRepository  @Inject constructor(private  val api: ApiService ): Serializable {


    private var currentChannel : String = "null"
    private var chat = mutableMapOf<Int , Message>();

    suspend fun sendMessage(message: String, user: User): Response<ChatRequest> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val formattedDate = dateFormat.format(Date())
        val request = ChatSendRequest(
            message = message,
            channel = currentChannel,
            user = user,
            date = formattedDate
        )
        return api.sendMessage(request);
    }
    suspend fun getAllMessages(channel: String) : List<Message>{
        currentChannel = channel
        chat.clear();
       val resp =  api.getAllMessages(channel)
        if(resp.isSuccessful && resp.body() != null) {
            val response = resp.body();
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            for(messageRequ in response!!) {
               chat[messageRequ.id] = (Message(messageRequ.id, messageRequ.message, format.parse(messageRequ.date), messageRequ.user?.userName, messageRequ.channel))
            }
            return chat.values.toList();
        }
        return emptyList();
    }

    fun addReceivedMessage(message: Message): Boolean {
        if(!chat.containsKey(message.id)) {
            chat[message.id] = message
            return true
        }
        return false
    }
}