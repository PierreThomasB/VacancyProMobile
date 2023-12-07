package com.project.vacancypromobile.services

import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/User/ListUser")
    suspend fun List() : Response<List<User>>
    @POST("api/User/SignIn")
    suspend fun SignIn(email: String, password: String) : Response<User>
    fun FetchUser(token: String) : Response<User>
    @POST("api/User/SignUp")
    fun signUp(lastname: String, firstname: String, email: String, password: String): Response<User>

    @POST("api/Period/CreatePeriod")
     fun createPeriod(name: String, description: String) : Response<Period>

}