package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import com.project.vacancypromobile.models.Place

data class PeriodsResultRequest (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("beginDate")
    var beginDate : String,
    @SerializedName("endDate")
    var endDate : String,
    @SerializedName("place")
    var place : Place
)