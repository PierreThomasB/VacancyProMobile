package com.project.vacancypromobile.datas

import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.services.ApiService
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class ActivityRepository @Inject constructor(private  val apiService: ApiService) : Serializable {

    suspend fun getActivitiesByPeriod(id : Int) : List<Activity>? {
        val resp = apiService.getActivitiesByPeriod(id)
        if(resp.isSuccessful && resp.body() != null) {
            val body = resp.body()
            val activities  = listOf<Activity>();
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            //TODO: fix this
            body?.forEach { activity ->
                activities.plus(
                    Activity(
                        id = activity.id,
                        name = activity.name,
                        description = activity.description,
                        startDate = format.parse(activity.beginDate),
                        endDate = format.parse(activity.endDate),
                        place = activity.place
                    )
                )
            }
            return activities;
        }
        return emptyList();
    }



}