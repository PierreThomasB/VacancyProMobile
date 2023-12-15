package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName

data class MeteoRequest (

    @SerializedName("location")
    val location : Any,
    @SerializedName("current")
    val current : Any,

    )