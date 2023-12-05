package com.project.vacancypromobile.datas

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.ApiService
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
        val token = tokenManager.getToken().first()
        if (token != null) {
            val response = apiService.FetchUser(token)
            if (response.isSuccessful) currentUser = response.body()
        }
    }

    suspend fun signIn(email: String, password: String): Boolean {
        val response = apiService.SignIn(email, password)
        if(response.isSuccessful) {
            currentUser = response.body()
            return true
        }
        return false
    }

    suspend fun signUp(lastname:String, firstname: String, email:String, password:String) {
        val response = apiService.signUp(lastname, firstname, email, password)
    }
}