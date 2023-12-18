package com.project.vacancypromobile.viewModel

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val userRepository: UserRepository , private val periodRepository: PeriodRepository): ViewModel() {
    private var user: User? = null
    var periods by mutableStateOf(emptyList<Period>());


    fun loadCurrentUser() {
        user = userRepository.getCurrentUser()
    }

    fun getUser(): User {
        user = userRepository.getCurrentUser()
        return if (user == null) User() else user as User

    }
    suspend fun getPeriods() {
        periods = periodRepository.getAllPeriods()
    }

    fun logout(activity: Activity?) {
        viewModelScope.launch {
            activity?.finish()
            userRepository.logout()
        }
    }


}