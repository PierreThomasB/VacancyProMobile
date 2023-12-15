package com.project.vacancypromobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

 class Period  (
     @SerializedName("id")
    val id: Int = 0,
     @SerializedName("name")
    val name: String = "",
     @SerializedName("description")
    val description: String = "",
     @SerializedName("beginDate")
     override val beginDate: Date,
     @SerializedName("endDate")
     override val endDate: Date,
     @SerializedName("place")
     val place : Place,
     @SerializedName("activities")
    val activities: List<Activity>
 ) : HasPeriodDates