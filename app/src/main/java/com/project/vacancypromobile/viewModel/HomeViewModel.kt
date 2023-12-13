package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val userRepository: UserRepository , private val periodRepository: PeriodRepository): ViewModel() {

    init {
        this.loadCurrentUser()
        if(this.getUser().username != null) {
            runBlocking { getPeriods() }
        }
    }

    private var user: User? = null
    var periods by mutableStateOf(emptyList<Period>());
    var count  by mutableIntStateOf(periods.size)


    fun loadCurrentUser() {
        user = userRepository.getCurrentUser()
    }

    fun getUser(): User {
        user = userRepository.getCurrentUser()
        return if (user == null) User() else user as User

    }
    private suspend fun getPeriods() {
        periods = periodRepository.getAllPeriod()
    }












}