package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.services.requests.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun updateEmail(input: String) { email = input }
    fun updatePassword(input: String) { password = input }
    suspend fun logIn() {
        val request = LoginRequest(email, password)
        userRepository.signIn(request)


    }
}