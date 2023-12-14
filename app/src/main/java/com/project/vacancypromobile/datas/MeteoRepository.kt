package com.project.vacancypromobile.datas

import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class MeteoRepository @Inject constructor(private  val apiService: ApiService) : Serializable {

    suspend fun getMeteoInfo() {
         val resp = apiService.getMeteoInfo()
        if(resp.isSuccessful) {
            val meteo = resp.body()
        }
        else {
            val error = resp.errorBody()
        }
    }
}