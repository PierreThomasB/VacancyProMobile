package com.project.vacancypromobile.models

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("id")
    val id : String ="",
    @SerializedName("username")
    val username: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("isAdmin")
    val isAdmin: Boolean = false,
    @SerializedName("token")
    val token: String = "",

)
