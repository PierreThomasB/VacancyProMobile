package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.services.requests.LoginRequest
import javax.inject.Inject

class LoginViewModel @Inject constructor()  : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    suspend fun logIn() {
        val request = LoginRequest(email, password)
        //userRepository.signIn(request)
    }


}
