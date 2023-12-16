package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.models.User

data class PeriodRequest(
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
    @SerializedName("listUser")
    val users: List<User>
)
