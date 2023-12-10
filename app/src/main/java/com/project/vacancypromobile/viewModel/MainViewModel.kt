package com.project.vacancypromobile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository : UserRepository): ViewModel() {
    fun loadUser() =  runBlocking {
        userRepository.loadUser()
    }

    fun getCurrentUser(): User? {
        return userRepository.getCurrentUser()
    }
}