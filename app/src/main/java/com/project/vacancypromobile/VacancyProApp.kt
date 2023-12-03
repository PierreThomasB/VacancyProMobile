package com.project.vacancypromobile

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VacancyProApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}