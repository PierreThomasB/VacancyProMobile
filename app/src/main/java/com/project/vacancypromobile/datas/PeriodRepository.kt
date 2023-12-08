package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class PeriodRepository @Inject constructor( val apiService: ApiService) : Serializable{

    suspend fun createPeriod(request : Period) {
        val response  = apiService.createPeriod(request)
        if(response.isSuccessful) {
            Log.d("Period","Period created")
        } else {
            Log.d("Period","Period not created")
        }
    }


}