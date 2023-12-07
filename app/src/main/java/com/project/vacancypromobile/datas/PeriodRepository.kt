package com.project.vacancypromobile.datas

import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class PeriodRepository @Inject constructor(private val apiService: ApiService) : Serializable{

    fun createPeriod(name: String, description: String) {
        apiService.createPeriod(name, description)
    }




}