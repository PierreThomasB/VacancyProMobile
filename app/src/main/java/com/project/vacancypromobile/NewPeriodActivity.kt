package com.project.vacancypromobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.project.vacancypromobile.ui.screens.NewPeriodScreen
import com.project.vacancypromobile.viewModel.NewPeriodViewModel

class NewPeriodActivity : ComponentActivity(){

    private val newPeriodViewModel : NewPeriodViewModel by viewModels<NewPeriodViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewPeriodScreen()
        }
    }






}