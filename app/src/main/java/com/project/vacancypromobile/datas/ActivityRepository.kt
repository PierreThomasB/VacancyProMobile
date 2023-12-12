package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import javax.inject.Inject

class ActivityRepository @Inject constructor(private  val apiService: ApiService) : Serializable {


    suspend fun getActivityByPeriod(id: Int) {
        val response  = apiService.getActivityByPeriod(id);
        if (response.isSuccessful && response.body() != null) {

            Log.d("Activity", "Activity found");
        } else {
            Log.e("Activity", "Activity not found")
        }
    }
}