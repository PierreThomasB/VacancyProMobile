package com.project.vacancypromobile.viewModel

import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private var user: User? = null

    fun loadCurrentUser() {
        user = userRepository.getCurrentUser()
    }

    fun getUser(): User {
        user = userRepository.getCurrentUser()
        return if (user == null) User() else user as User

    }


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val periodRepository: PeriodRepository
) : ViewModel(){

    var periods by mutableStateOf(emptyList<Period>());

    suspend fun getPeriods() {
        periods = periodRepository.getAllPeriod()
    }







}