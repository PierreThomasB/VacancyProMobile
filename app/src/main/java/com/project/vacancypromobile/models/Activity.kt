package com.project.vacancypromobile.models

import java.util.Calendar
import java.util.Date

  class Activity (
     val id: Int = 0 ,
     val name: String,
     val description: String,
     override val beginDate: Date,
     override val endDate: Date,
     val place: Place,
  ) : HasPeriodDates{

     fun convertDateToCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal
     }

  }


