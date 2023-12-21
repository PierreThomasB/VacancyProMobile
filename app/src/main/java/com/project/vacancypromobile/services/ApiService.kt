package com.project.vacancypromobile.services

import com.project.vacancypromobile.models.Activity
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.requests.ActivityListRequest
import com.project.vacancypromobile.services.requests.ActivityRequest
import com.project.vacancypromobile.services.requests.ChatRequest
import com.project.vacancypromobile.services.requests.ChatSendRequest
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.services.requests.PeriodRequest
import com.project.vacancypromobile.services.requests.PeriodsResultRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {


    /** USERS **/
    @GET("User/ListUser")
    suspend fun List(@Query("periodId") periodId: Int) : Response<List<User>>
    @POST("User/SignIn")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<User>
    /*@GET("User")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun fetchUser(@Header("Autorization") token: String) : Response<User>
*/

    @GET("User")
    suspend fun fetchUser() : Response<User>
    /** PERIODS **/
    @GET("Period/PeriodByUser")
    suspend fun getAllPeriod() : Response<List<PeriodsResultRequest>>

    @POST("Period/NewVacances")
    suspend fun createPeriod(@Body request : PeriodRequest) : Response<Period>
    @DELETE("Period/Delete")
    abstract suspend  fun deletePeriod(@Query("id")id: Int) : Response<Any>

    /** METEO **/
    @GET("Meteo/GetMeteo")
    suspend fun getMeteoInfo(@Query("lieu") ville : String) : Response<Any>

    /** ACTIVITIES **/
    @GET("Activity/ActivityByPeriod")
    suspend fun getActivitiesByPeriod(@Query("id") periodId : Int) : Response<List<ActivityListRequest>>

    /** MESSAGES*/
    @GET("Chat/AllMessage")
    suspend fun getAllMessages(@Query("channel") channel : String) : Response<List<ChatRequest>>
    @POST("Chat/NewMessage")
    suspend fun sendMessage(@Body chatSendRequest: ChatSendRequest ) : Response<ChatRequest>
    @POST("Activity/NewActivity")
    suspend fun createActivity(@Body request: ActivityRequest): Response<Activity>
    @PUT("Activity/UpdateActivity")
    suspend fun updateActivity(@Body request: ActivityRequest): Response<Activity>

    @PUT("Period/AddUser")
    suspend fun addUserToPeriod(@Query("userId") userId: String, @Query("Period") periodId: Int): Response<Any>


}