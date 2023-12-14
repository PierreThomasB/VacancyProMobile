package com.project.vacancypromobile.services

import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.requests.ActivityListRequest
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.services.requests.PeriodsResultRequest
import com.project.vacancypromobile.services.requests.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    /** USERS **/
    @GET("api/User/ListUser")
    suspend fun List() : Response<List<User>>
    @POST("api/User/SignIn")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<User>
    @GET("api/User")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun fetchUser(@Header("Autorization") token: String) : Response<User>
    @GET("api/User")
    suspend fun fetchUser() : Response<User>
    @POST("api/User/SignUp")
    suspend fun signUp(@Body registerRequest: RegisterRequest): Response<User>



    /** PERIODS **/
    @GET("api/Period/PeriodByUser")
    suspend fun getAllPeriod() : Response<List<PeriodsResultRequest>>
    @GET("api/Period/Period")
    suspend fun getPeriod(@Query("id") id : Int) : Response<PeriodsResultRequest>
    @POST("api/Period/CreatePeriod")
    suspend fun createPeriod(request : Period) : Response<Period>



    /** METEO **/
    @GET("api/Meteo/GetMeteoInfo")
    suspend fun getMeteoInfo() : Response<String>

    /** ACTIVITIES **/
    @GET("api/Activity/ActivityByPeriod")
    suspend fun getActivitiesByPeriod(@Query("id") periodId : Int) : Response<List<ActivityListRequest>>


}