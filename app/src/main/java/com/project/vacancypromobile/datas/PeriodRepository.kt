package com.project.vacancypromobile.datas

import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class PeriodRepository @Inject constructor(private val apiService: ApiService) : Serializable{





    suspend fun createPeriod(request : PeriodRequest) {
        val response  = apiService.createPeriod(request)
        if(response.isSuccessful) {
            println("Period created")
        } else {
            println("Period not created")
        }
    }


}