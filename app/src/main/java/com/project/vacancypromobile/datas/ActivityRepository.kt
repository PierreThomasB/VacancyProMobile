package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class ActivityRepository @Inject constructor(private  val apiService: ApiService) : Serializable {



    private val activities = mutableMapOf<Int, Activity>()

    suspend fun getActivitiesByPeriod(id : Int) : List<Activity>? {
        activities.clear();
        val resp = apiService.getActivitiesByPeriod(id)
        if(resp.isSuccessful && resp.body() != null) {
            val body = resp.body()
            val activities = mutableListOf<Activity>()
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            body?.forEach { activity ->

                val act =  Activity(
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
            return activities;
        }
        return emptyList();
    }



}