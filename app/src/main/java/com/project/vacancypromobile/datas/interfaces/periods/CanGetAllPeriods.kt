package com.project.vacancypromobile.datas.interfaces.periods

import com.project.vacancypromobile.models.Period

interface CanGetAllPeriods {
    suspend fun getAllPeriods() :  List<Period>
}