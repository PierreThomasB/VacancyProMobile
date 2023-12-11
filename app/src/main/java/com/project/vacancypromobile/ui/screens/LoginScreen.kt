package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.LoginViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    VacancypromobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    OutlinedTextField(
                        value = loginViewModel.email,
                        onValueChange = {
                            email -> loginViewModel.updateEmail(email)
                        },
                        label = {
                            Text("Email")
                        },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null)}
                    )
                    OutlinedTextField(
                        value = loginViewModel.password,
                        onValueChange = {
                            password -> loginViewModel.updatePassword(password)
                        },
                        label = {
                            Text("Mot de passe")
                        },
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null)}
                    )
                    OutlinedButton(
                        onClick = { runBlocking {
                        loginViewModel.logIn()
                        navController.navigate(Screen.Home.route)

                    }

                        }) {
                        Text(text = "Se connecter")
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    //LoginScreen()
}