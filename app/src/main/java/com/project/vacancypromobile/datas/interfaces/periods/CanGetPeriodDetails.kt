package com.project.vacancypromobile.datas.interfaces.periods

import com.project.vacancypromobile.models.Period

interface CanGetPeriodDetails {
     fun getPeriodDetails(id : Int) : Period?
}