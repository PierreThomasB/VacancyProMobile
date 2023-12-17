package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ChatReceiveRequest (
    @SerializedName("Id")
    val id : Int,
    @SerializedName("Message")
    val message : String,
    @SerializedName("Date")
    val date : Date,
    @SerializedName("Channel")
    val channel : String


    )