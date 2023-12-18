package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.Period
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

class UserRepository @Inject constructor(
    val tokenManager: TokenManager,
    val apiService: ApiService
) : Serializable {
    private val users = arrayListOf<User>()
    private val _users = mutableMapOf<String, User>()
    private var currentUser: User? = null

    fun getUsers() = users
    fun getCurrentUser() = currentUser


    suspend fun loadUsers(periodId: Int): Map<String, User> {
        _users.clear()
        val response = apiService.List(periodId)
        if (response.isSuccessful) {
            response.body()?.forEach {
                _users[it.id] = User(it.id, it.username, it.email, it.isAdmin, it.token)
            }
            return _users
        } else {
            return emptyMap()
        }

    }

    fun getUser(id: String): User? {
        if (_users.isEmpty() || !_users.containsKey(id)) {
            return null
        }
        return _users[id]
    }

    suspend fun loadUser() {
        val response = apiService.fetchUser()
        if (response.isSuccessful) {
            currentUser = response.body()
            tokenManager.saveToken(currentUser!!.token)
        }
    }

    suspend fun signIn(request: LoginRequest): Boolean {
        val response = apiService.signIn(request)
        if (response.isSuccessful) {
            currentUser = response.body()
            tokenManager.saveToken(currentUser!!.token)
        }
        return response.isSuccessful
    }

    suspend fun logout() {
        tokenManager.deleteToken()
        currentUser = null
    }
}