package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    VacancypromobileTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = { Text("Vacancy Pro") }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {

            }

        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}