package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.services.requests.RegisterRequest
import com.project.vacancypromobile.utils.TokenManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

class UserRepository @Inject constructor(val tokenManager: TokenManager, val apiService: ApiService) : Serializable {
    private val users = arrayListOf<User>()
    private var currentUser: User? = null

    fun getUsers() = users
    fun getCurrentUser() = currentUser


    fun loadUsers() {
        users.clear()
        GlobalScope.launch {
            val response = apiService.List()
            if (response.isSuccessful) {
                response.body()?.let {
                    for (user in it) users.add(user)
                }
            }
        }
    }

    suspend fun loadUser() {
        /*val token = tokenManager.getToken().first()
        if (token != null) {
            //val response = apiService.fetchUser("Bearer: $token")
            val response = apiService.fetchUser()
            if (response.isSuccessful) {
                currentUser = response.body()
            }
        }*/
        val response = apiService.fetchUser()
        if(response.isSuccessful) {
            currentUser = response.body()
            tokenManager.saveToken(currentUser!!.token)
        }
    }

    suspend fun signIn(request: LoginRequest): Boolean {
        val response = apiService.signIn(request)
        if(response.isSuccessful) {
            currentUser = response.body()
            tokenManager.saveToken(currentUser!!.token)
        }
        return response.isSuccessful
    }

    suspend fun signUp(request: RegisterRequest) {
        val response = apiService.signUp(request)
        if (response.isSuccessful) {
            currentUser = response.body()
            Log.d("USER", response.body().toString())
            tokenManager.saveToken(currentUser!!.token)
        }
    }
}