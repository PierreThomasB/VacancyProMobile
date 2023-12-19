package com.project.vacancypromobile.datas.interfaces.periods

interface CanDeletePeriods {
    suspend fun deletePeriod(id : Int)
}