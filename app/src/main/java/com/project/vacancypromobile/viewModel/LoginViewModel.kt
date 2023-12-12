package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.ui.screens.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    var isSnackBarShowing: Boolean by mutableStateOf(false)
        private set
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var loginSuccess = false
    private fun showSnackBar() {
        isSnackBarShowing = true
    }
    fun dismissSnackBar() {
        isSnackBarShowing = false
    }



    fun updateEmail(input: String) { email = input }
    fun updatePassword(input: String) { password = input }
    suspend fun logIn() {
        val request = LoginRequest(email, password)
        loginSuccess = userRepository.signIn(request)

    }
}