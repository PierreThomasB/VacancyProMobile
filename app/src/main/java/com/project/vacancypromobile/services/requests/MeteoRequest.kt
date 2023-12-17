package com.project.vacancypromobile.services.requests

import com.google.gson.annotations.SerializedName



 data class Curent(
    @SerializedName("temperature")
    val temperature : Double,
    @SerializedName("weather_icons")
    val icon : List<String>,
    @SerializedName("weather_descriptions")
    val condition : List<String>,

)

  data class Localisation(

    @SerializedName("name")
    val name : String,
    @SerializedName("country")
    val country : String,

    )

 data class MeteoRequest (

    @SerializedName("location")
    val location : Localisation,
    @SerializedName("current")
    val current : Curent,

    )