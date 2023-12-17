package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import com.project.vacancypromobile.models.User

data class ChatSendRequest (

    @SerializedName("Message")
    val message:String,
    @SerializedName("Channel")
    val channel : String,
    @SerializedName("Date")
    val date : String ,
    @SerializedName("User")
    val user : User


    )