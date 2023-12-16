package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import com.project.vacancypromobile.models.User
import java.util.Date

data class ChatSendRequest (

    @SerializedName("Message")
    val message:String,
    @SerializedName("Channel")
    val channel : String,
    @SerializedName("Date")
    val date : Date = Date(),
    @SerializedName("User")
    val user : User


    )