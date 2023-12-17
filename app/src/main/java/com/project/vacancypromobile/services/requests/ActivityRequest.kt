package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place

data class ActivityRequest(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("beginDate")
    val beginDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("place")
    val place: Place,
    @SerializedName("period")
    val period: PeriodRequest
)