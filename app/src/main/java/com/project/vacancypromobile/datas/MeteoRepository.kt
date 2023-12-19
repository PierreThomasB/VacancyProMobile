package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Meteo
import com.project.vacancypromobile.services.ApiService
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.Serializable
import javax.inject.Inject

class MeteoRepository @Inject constructor(private  val apiService: ApiService) : Serializable {

    suspend fun getMeteoInfo(ville:String) : Meteo? {
        val resp = apiService.getMeteoInfo(ville)
        if(resp.isSuccessful && resp.body() != null) {
            val response   = resp.body() as String;
            val jsonObj: JsonObject = Json.parseToJsonElement(response).jsonObject
            val current = jsonObj["current"]?.jsonObject
            val location = jsonObj["location"]?.jsonObject
            val description = current!!["weather_descriptions"]?.jsonArray;
            val urlPhoto = current!!["weather_icons"]?.jsonArray;


            val name = location!!["name"].toString();
            val temperature = current!!["temperature"].toString()

            return Meteo(name ,temperature , description?.getOrNull(0).toString(), urlPhoto?.getOrNull(0).toString() );
        }
        return null;
    }
}