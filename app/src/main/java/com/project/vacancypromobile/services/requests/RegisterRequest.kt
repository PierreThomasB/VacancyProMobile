package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("firstname")
    var firstname:String,
    @SerializedName("lastname")
    var lastname:String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String

)