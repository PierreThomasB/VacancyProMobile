package com.project.vacancypromobile.viewModel

import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PeriodDetailViewModel @Inject constructor(private val periodRepository: PeriodRepository )  : ViewModel() {



    fun initPeriodDetails(id : Int) {
        runBlocking {
            period = getPeriodDetails(id)
        }
    }


     var period : Period? = null ;





    private suspend fun getPeriodDetails(id : Int) : Period {

        val resp =  periodRepository.getPeriodById(id)
        if(resp != null) {
            return resp;
        }
        return Period(0,"","", Date(),Date(), Place("0","",""), emptyList());
    }




}