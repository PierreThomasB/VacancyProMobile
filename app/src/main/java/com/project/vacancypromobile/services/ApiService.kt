package com.project.vacancypromobile.services

import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("api/User/ListUser")
    suspend fun List() : Response<List<User>>
    @POST("api/User/SignIn")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<User>
    @POST("api/User")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun fetchUser(@Header("Autorization") token: String) : Response<User>

    @POST("api/Period/CreatePeriod")
      suspend fun createPeriod(request : Period) : Response<Period>
    @POST("api/User/SignUp")
    suspend fun signUp(lastname: String, firstname: String, email: String, password: String): Response<User>
}