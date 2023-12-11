package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class PeriodRepository @Inject constructor(private  val apiService: ApiService) : Serializable{



    private val _periods = mutableListOf<Period>();

    suspend fun createPeriod(request : Period) {
        val response  = apiService.createPeriod(request)
        if(response.isSuccessful && response.body() != null) {
            _periods.add(request)
            Log.d("Period","Period created")
        } else {
            Log.d("Period","Period not created")
        }
    }


    suspend fun getAllPeriod() : List<Period> {
        val response = apiService.getAllPeriod()
        if(response.isSuccessful && response.body() != null) {
            _periods.addAll(response.body()!!)
            return _periods
        } else {
            Log.d("Period","Period not found")
            return emptyList()
        }
    }



}