package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName

data class ChatSendRequest (

    @SerializedName("Message")
    val message:String,
    @SerializedName("Channel")
    val channel : String,
    @SerializedName("Date")
    val date : String ,
    @SerializedName("UserName")
    val userName : String


    )