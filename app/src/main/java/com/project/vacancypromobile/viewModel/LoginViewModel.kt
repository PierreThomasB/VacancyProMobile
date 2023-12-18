package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    /*private val _loginState = MutableSharedFlow<Boolean>()
    val loginState = _loginState.asSharedFlow()*/

    private val _loginMessage = MutableLiveData<String>()
    val loginMessage: LiveData<String> get() = _loginMessage

    var errorMsg = "Connexion échouée"

    /*private fun setLoginState(state: Boolean) {
        viewModelScope.launch {
            _loginState.emit(state)
        }
    }*/


    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var loginSuccess = false



    fun updateEmail(input: String) { email = input }
    fun updatePassword(input: String) { password = input }
    private fun verifyData(): Boolean {
        val regex = Regex("^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$")
        if (email.isEmpty()) {
            _loginMessage.value = "Email manquant"
            return false
        }
        if (!regex.matches(email)) {
            _loginMessage.value = "Email invalide"
            return false
        }
        if (password.isEmpty()) {
            _loginMessage.value = "Mot de passe manquant"
            return false
        }
        return true
    }

    suspend fun logIn() {
        if(verifyData()) {
            val request = LoginRequest(email, password)
            loginSuccess = userRepository.signIn(request)
            if (loginSuccess) _loginMessage.value = "Connexion réussie !"
            else _loginMessage.value = "Connexion échouée"

        }

    }
}