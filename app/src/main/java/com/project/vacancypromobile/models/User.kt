package com.project.vacancypromobile.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    var username: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("isAdmin")
    var isAdmin: Boolean = false,
    @SerializedName("token")
    var token: String = ""
)
