package com.project.vacancypromobile.datas.interfaces.periods

interface CanAddUserToPeriod {
    suspend fun addUserToPeriod(userId: String, periodId: Int) : Boolean
}