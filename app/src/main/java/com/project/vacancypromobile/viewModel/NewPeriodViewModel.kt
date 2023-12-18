package com.project.vacancypromobile.viewModel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.requests.PeriodRequest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewPeriodViewModel @Inject constructor(
    private val periodRepository: PeriodRepository,
    private val application: Application
) :
    ViewModel() {

    private val _newPeriodMessage = MutableLiveData<String>()
    val newPeriodMessage: MutableLiveData<String> get() = _newPeriodMessage


    var newPeriodSuccess = false

    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")
    var periodStartDate by mutableStateOf<String>("")
    var periodEndDate by mutableStateOf<String>("")
    var periodPlace by mutableStateOf("")
    private lateinit var periodPlaceId: String

    val predictions = MutableLiveData<List<AutocompletePrediction>>()

    fun getPredictions(input: String) {
        viewModelScope.launch {
            val placesClient = Places.createClient(application)
            val request = FindAutocompletePredictionsRequest.builder().setQuery(input).build()
            val response = placesClient.findAutocompletePredictions(request)
            response.addOnCompleteListener { task ->
                predictions.value = task.result.autocompletePredictions
            }
        }
    }


    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    fun updatePeriodStartDate(periodStartDate: String) {
        //Log.d("Testing ", periodStartDate)
        this.periodStartDate = periodStartDate
    }

    fun updatePeriodEndDate(periodEndDate: String) {
        //Log.d("Testing ", periodEndDate)
        this.periodEndDate = periodEndDate
    }

    fun updatePeriodPlace(periodPlace: String, placeId: String) {
        //getPredictions(periodPlace)
        this.periodPlace = periodPlace
        this.periodPlaceId = placeId
    }

    fun getDatesFormatted(): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateDebut = format.parse(periodStartDate)
        val dateFin = format.parse(periodEndDate)
        val format2 = SimpleDateFormat("dd-MM-yyyy")
        return format2.format(dateDebut) + " -> " + format2.format(dateFin)
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
        if (periodPlace.isEmpty()) {
            _newPeriodMessage.value = "Lieu manquant"
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

        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createPeriod() {
        Log.d("Testing ", "Wow ");
        if (verifyData()) {
            val request = PeriodRequest(
                name = periodName,
                description = periodDescription,
                beginDate = periodStartDate,
                endDate = periodEndDate,
                place = Place(periodPlace, periodPlaceId, "null"),
                users = emptyList<User>()
            )
            newPeriodSuccess = periodRepository.createPeriod(request);
            if (newPeriodSuccess) {
                _newPeriodMessage.value = "Période ajoutée !"
                clearViewModel()
            } else _newPeriodMessage.value = "Erreur lors de l'ajout de la période"
        }
    }

    private fun clearViewModel() {
        periodName = ""
        periodDescription = ""
        periodStartDate = ""
        periodEndDate = ""
        periodPlace = ""
        periodPlaceId = ""
        _newPeriodMessage.value = ""
    }

}