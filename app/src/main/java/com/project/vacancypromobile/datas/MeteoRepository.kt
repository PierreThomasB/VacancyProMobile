package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Meteo
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class MeteoRepository @Inject constructor(private  val apiService: ApiService) : Serializable {

    suspend fun getMeteoInfo(ville:String) : Meteo? {
        val resp = apiService.getMeteoInfo(ville)
        if(resp.isSuccessful && resp.body() != null) {
            val response = resp.body()


        }
        else {
            val error = resp.errorBody()
        }
        return null;
    }
}