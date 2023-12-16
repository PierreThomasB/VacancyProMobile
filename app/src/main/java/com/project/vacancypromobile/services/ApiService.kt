package com.project.vacancypromobile.services

import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.requests.ActivityListRequest
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.services.requests.PeriodRequest
import com.project.vacancypromobile.services.requests.PeriodsResultRequest
import com.project.vacancypromobile.services.requests.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/User/ListUser")
    suspend fun List() : Response<List<User>>
    @POST("api/User/SignIn")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<User>
    @GET("api/User")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun fetchUser(@Header("Autorization") token: String) : Response<User>

    @POST("api/Period/NewVacances")
    suspend fun createPeriod(@Body request : PeriodRequest) : Response<Period>
   // suspend fun fetchUser(@Header("Autorization") token: String) : Response<User>
    @GET("api/User")
    suspend fun fetchUser() : Response<User>
    @GET("api/Period/PeriodByUser")
    suspend fun getAllPeriod() : Response<List<PeriodsResultRequest>>

    @GET("api/Activity/ActivityByPeriod?id={id}")
    suspend fun getActivityByPeriod(@Path("id") id : Int) : Response<List<ActivityListRequest>>


}