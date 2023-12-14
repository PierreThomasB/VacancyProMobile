package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class PeriodRepository @Inject constructor(private  val apiService: ApiService) : Serializable {


    private var _periods = mutableListOf<Period>();

    suspend fun createPeriod(request: Period) {
        val response = apiService.createPeriod(request)
        if (response.isSuccessful && response.body() != null) {
            _periods.add(request)
            Log.d("Period", "Period created")
        } else {
            Log.d("Period", "Period not created")
        }
    }


    suspend fun getAllPeriod(): List<Period> {
        _periods.clear();
        val response = apiService.getAllPeriod()
        if (response.isSuccessful && response.body() != null) {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            response.body()?.forEach {
                _periods.add(Period(it.id, it.name, it.description, format.parse(it.beginDate),  format.parse(it.endDate) , it.place ,emptyList()));
            }
            Log.d("Period", "Periods found");
            return _periods;
        } else {
            Log.d("Period", "Period not found")
            return emptyList()
        }
    }

    suspend fun getPeriodById(id : Int) : Period? {
        val response = apiService.getPeriodById(id)
        if (response.isSuccessful && response.body() != null) {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val resp = response.body()
            if(resp != null) {
                return Period(resp.id, resp.name, resp.description, format.parse(resp.beginDate),  format.parse(resp.endDate) , resp.place ,emptyList());
            }
            return null;
        } else {
            Log.d("Period", "Period not found")
            return null
        }
    }



}



