package com.project.vacancypromobile.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun updateEmail(input: String) { email = input }
    fun updatePassword(input: String) { password = input }
    fun logIn() {
        Log.d("LoginViewModel", "Email: $email")

    }
}