package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.services.requests.EditActivityRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class EditActivityViewModel @Inject constructor(private val activityRepository: ActivityRepository) : ViewModel() {

    private val _editActivityMessage = MutableLiveData<String>()
    private var id: Int = 0
    val editActivityMessage: MutableLiveData<String> get() = _editActivityMessage
    var activityName by mutableStateOf<String>("");
    var activityDescription by mutableStateOf<String>("");
    var activityStartDate by mutableStateOf<String>("");
    var activityEndDate by mutableStateOf<String>("");
    var editActivitySuccess by mutableStateOf(false)
    fun init(id: Int) {
        if (id != 0) {
            this.id = id
            runBlocking {
                getActivityDetails(id)
            }
        }
    }

    private fun getActivityDetails(id: Int) {
        val activity = activityRepository.getActivityDetails(id)
        activityName = activity.name
        activityDescription = activity.description
        /*activityStartDate = activity.beginDate.toString()
        activityEndDate = activity.endDate.toString()*/

    }
    fun getDatesFormatted(): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateDebut = format.parse(activityStartDate)
        val dateFin = format.parse(activityEndDate)
        val format2 = SimpleDateFormat("dd-MM-yyyy")
        return format2.format(dateDebut) + " -> " + format2.format(dateFin)
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

    fun editActivity(activityId: Int) {
        if (verifyData()) {
            viewModelScope.launch {
                val editActivityRequest = EditActivityRequest(
                    name = activityName,
                    description = activityDescription,
                    beginDate = activityStartDate,
                    endDate = activityEndDate
                )
                editActivitySuccess = activityRepository.updateActivity(activityId, editActivityRequest)
                if (editActivitySuccess) {
                    _editActivityMessage.value = "Activité modifiée"
                    clearViewModel()
                } else {
                    _editActivityMessage.value = "Erreur lors de la modification de l'activité"
                }
            }
        }
    }

    private fun clearViewModel() {
        getActivityDetails(this.id)
    }

    private fun verifyData(): Boolean {
        if (activityName.isEmpty()) {
            _editActivityMessage.value = "Nom manquant"
            return false
        }
        if (activityDescription.isEmpty()) {
            _editActivityMessage.value = "Description manquante"
            return false
        }
        if (activityStartDate.isEmpty()) {
            _editActivityMessage.value = "Date de début manquante"
            return false
        }
        if (activityEndDate.isEmpty()) {
            _editActivityMessage.value = "Date de fin manquante"
            return false
        }
        if (activityStartDate > activityEndDate) {
            _editActivityMessage.value = "La date de début doit être inférieure à la date de fin"
            return false
        }

        return true
    }
}
