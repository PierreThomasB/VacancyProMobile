package com.project.vacancypromobile.models

data class Activity (
    val id: Int,
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val place: Place,
)