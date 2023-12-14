package com.project.vacancypromobile.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewPeriodViewModel @Inject constructor(private val periodRepository: PeriodRepository) :
    ViewModel() {

    private val _newPeriodMessage = MutableLiveData<String>()
    val newPeriodMessage: MutableLiveData<String> get() = _newPeriodMessage
    var newPeriodSuccess = false


    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")
    var periodStartDate by mutableStateOf<String>("")
    var periodEndDate by mutableStateOf<String>("")
    private var periodPlace by mutableStateOf("")

    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    fun updatePeriodStartDate(periodStartDate: String) {
        Log.d("Testing ", periodStartDate)
        this.periodStartDate = periodStartDate
    }

    fun updatePeriodEndDate(periodEndDate: String) {
        Log.d("Testing ", periodEndDate)
        this.periodEndDate = periodEndDate
    }

    fun updatePeriodPlace(periodPlace: String) {
        this.periodPlace = periodPlace
    }

    private fun verifyData(): Boolean {
        if (periodName.isEmpty()) {
            _newPeriodMessage.value = "Nom manquant"
            return false
        }
        if (periodDescription.isEmpty()) {
            _newPeriodMessage.value = "Description manquante"
            return false
        }
        if (periodStartDate.isEmpty()) {
            _newPeriodMessage.value = "Date de début manquante"
            return false
        }
        if (periodEndDate.isEmpty()) {
            _newPeriodMessage.value = "Date de fin manquante"
            return false
        }
        if (periodPlace.isEmpty()) {
            _newPeriodMessage.value = "Lieu manquant"
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createPeriod() {
        Log.d("Testing ", "Wow ");
        if (verifyData()) {
            val period = Period(
                name = periodName,
                description = periodDescription,
                beginDate = Date(),
                endDate = Date(),
                place = Place("null", "null", "null"),
                activities = emptyList()

            );
            newPeriodSuccess = periodRepository.createPeriod(period);
            if (newPeriodSuccess) _newPeriodMessage.value = "Période créée !"
            else _newPeriodMessage.value = "Période non créée"
        }
    }
}