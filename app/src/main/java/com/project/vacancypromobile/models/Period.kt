package com.project.vacancypromobile.models

import com.google.gson.annotations.SerializedName

data class Period (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("description")
    var description: String = "",
)