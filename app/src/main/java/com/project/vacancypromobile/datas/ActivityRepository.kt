package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.ActivityRequest
import com.project.vacancypromobile.services.requests.EditActivityRequest
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class ActivityRepository @Inject constructor(private val apiService: ApiService) : Serializable {


    private val activities = mutableMapOf<Int, Activity>()

    suspend fun getActivitiesByPeriod(id: Int): List<Activity>? {
        activities.clear();
        val resp = apiService.getActivitiesByPeriod(id)
        if (resp.isSuccessful && resp.body() != null) {
            val body = resp.body()
            val activities = mutableListOf<Activity>()
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            body?.forEach { activity ->

                val act = Activity(
                    id = activity.id,
                    name = activity.name,
                    description = activity.description,
                    beginDate = format.parse(activity.beginDate),
                    endDate = format.parse(activity.endDate),
                    place = activity.place
                )
                activities.add(act)
                this.activities[activity.id] = act
            }
            return activities.sortedBy { it.beginDate }
        }
        return emptyList();
    }

    suspend fun createActivity(request: ActivityRequest): Boolean {
        val resp = apiService.createActivity(request)
        if (resp.isSuccessful && resp.body() != null) {
            Log.d("Period", "Period created")
        } else {
            Log.d("Period", "Period not created")
        }
        return resp.isSuccessful
    }



    suspend fun updateActivity(id: Int, request: EditActivityRequest): Boolean {
        val resp = apiService.updateActivity(id, request)
        activities[id]!!.name = request.name
        activities[id]!!.description = request.description
        if (resp.isSuccessful && resp.body() != null) {
            Log.d("Period", "Period updated")
        } else {
            Log.d("Period", "Period not updated")
        }
        return resp.isSuccessful
    }

    fun getActivityDetails(id: Int): Activity {
        return activities[id]!!
    }


}