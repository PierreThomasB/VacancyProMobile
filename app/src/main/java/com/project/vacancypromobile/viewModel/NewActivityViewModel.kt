package com.project.vacancypromobile.viewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Place
import com.project.vacancypromobile.services.requests.ActivityRequest
import com.project.vacancypromobile.services.requests.PeriodRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewActivityViewModel @Inject constructor(
    private val periodRepository: PeriodRepository,
    private val activityRepository: ActivityRepository,
    private val application: Application
) : ViewModel() {
    var periodRequest: PeriodRequest? = null
    private val _newActivityMessage = MutableLiveData<String>()
    val newActivityMessage: MutableLiveData<String> get() = _newActivityMessage

    var newActivitySuccess = false

    var activityName by mutableStateOf<String>("");
    var activityDescription by mutableStateOf<String>("");
    var activityStartDate by mutableStateOf<String>("");
    var activityEndDate by mutableStateOf<String>("");
    var activityPlace by mutableStateOf<String>("")
    private lateinit var periodPlaceId: String

    val predictions = MutableLiveData<List<AutocompletePrediction>>()

    fun init(id: Int) {
        if (id != 0) {
            viewModelScope.launch {
                getPeriodDetails(id)
            }
        }
    }

    private fun getPeriodDetails(id: Int) {
        val period = periodRepository.getPeriodDetails(id)!!

        this.periodRequest = PeriodRequest(
            id = period.id,
            name = period.name,
            description = period.description,
            beginDate = formatPeriodDate(period.beginDate),
            endDate = formatPeriodDate(period.endDate),
            place = period.place,
            users = emptyList()
        )
    }
    fun getDatesFormatted(): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateDebut = format.parse(activityStartDate)
        val dateFin = format.parse(activityEndDate)
        val format2 = SimpleDateFormat("dd-MM-yyyy")
        return format2.format(dateDebut) + " -> " + format2.format(dateFin)
    }
    private fun formatPeriodDate(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return format.format(date)
    }

    fun updateActivityName(activityName: String) {
        this.activityName = activityName
    }

    fun updateActivityDescription(activityDescription: String) {
        this.activityDescription = activityDescription
    }

    fun updateActivityStartDate(activityStartDate: String) {
        this.activityStartDate = activityStartDate
    }

    fun updateActivityEndDate(activityEndDate: String) {
        this.activityEndDate = activityEndDate
    }

    fun updateActivityPlace(place: String, placeId: String) {
        this.activityPlace = place
        this.periodPlaceId = placeId
    }

    suspend fun createActivity(periodId: String?) {
        val id = periodId?.toIntOrNull()
        getPeriodDetails(id!!)
        if (verifyData()) {
            val request = ActivityRequest(
                name = activityName,
                description = activityDescription,
                beginDate = activityStartDate,
                endDate = activityEndDate,
                place = Place(activityPlace, periodPlaceId, "null"),
                period = periodRequest!!
            )
            newActivitySuccess = activityRepository.createActivity(request)
            if (newActivitySuccess) {
                _newActivityMessage.value = "Activité ajoutée !"
                clearViewModel()
            } else {
                _newActivityMessage.value = "Erreur lors de l'ajout de l'activité"
            }
        }
    }

    private fun clearViewModel() {
        activityName = ""
        activityDescription = ""
        activityStartDate = ""
        activityEndDate = ""
        activityPlace = ""
        _newActivityMessage.value = ""
    }

    private fun verifyData(): Boolean {
        if (periodRequest == null) {
            _newActivityMessage.value = "Erreur lors de la récupération de la période"
            return false
        }
        if (activityName.isEmpty()) {
            _newActivityMessage.value = "Nom manquant"
            return false
        }
        if (activityDescription.isEmpty()) {
            _newActivityMessage.value = "Description manquante"
            return false
        }
        if (activityPlace.isEmpty()) {
            _newActivityMessage.value = "Lieu manquant"
            return false
        }
        if (activityStartDate.isEmpty()) {
            _newActivityMessage.value = "Date de début manquante"
            return false
        }
        if (activityEndDate.isEmpty()) {
            _newActivityMessage.value = "Date de fin manquante"
            return false
        }
        if (activityStartDate > activityEndDate) {
            _newActivityMessage.value = "La date de début doit être inférieure à la date de fin"
            return false
        }
        if (activityStartDate < periodRequest!!.beginDate) {
            _newActivityMessage.value = "La date de début doit être supérieure à la date de début de la période"
            return false
        }
        if (activityEndDate > periodRequest!!.endDate) {
            _newActivityMessage.value = "La date de fin doit être inférieure à la date de fin de la période"
            return false
        }

        return true
    }



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


}