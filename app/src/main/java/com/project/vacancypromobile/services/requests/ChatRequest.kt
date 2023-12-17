package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName



data class ChatUserRequest(
    @SerializedName("userName")
    val userName: String? = null,
)


data class ChatRequest (
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("message")
    val message: String = "null",
    @SerializedName("channel")
    val channel: String = "null",
    @SerializedName("date")
    val date: String = "null",
    @SerializedName("user")
    val user: ChatUserRequest? = null,
    )