package com.project.vacancypromobile.viewModel

import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Period


class PeriodViewModel constructor(period: Period) : ViewModel() {
    val nom = period.name;
    val description  = period.description;






}