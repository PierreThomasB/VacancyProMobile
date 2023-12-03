package com.project.vacancypromobile.models

data class User(
    var username: String = "",
    var email: String = "",
    var isAdmin: Boolean = false,
    var token: String = ""
)
