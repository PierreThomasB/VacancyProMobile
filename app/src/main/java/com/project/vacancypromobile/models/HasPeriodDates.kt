package com.project.vacancypromobile.models

import java.text.SimpleDateFormat
import java.util.Date

 interface HasPeriodDates {

     val beginDate : Date

     val endDate : Date

    fun afficherDate() : String {
        val format = SimpleDateFormat("dd/MM/yy")
        val dateFormate = format.format(beginDate);
        val dateFormate2 = format.format(endDate);
        return "Du ${dateFormate.toString()} au ${dateFormate2.toString()}"
    }
}