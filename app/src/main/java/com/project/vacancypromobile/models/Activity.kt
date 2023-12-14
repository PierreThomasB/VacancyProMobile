package com.project.vacancypromobile.models

import java.util.Date

data class Activity (
    val id: Int,
    val name: String,
    val description: String,
    val startDate: Date,
    val endDate: Date,
    val place: Place,
)