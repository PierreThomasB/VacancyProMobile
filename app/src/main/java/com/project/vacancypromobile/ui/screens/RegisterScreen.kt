package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.RegisterViewModel
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navController: NavController) {
    VacancypromobileTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = { Text("Vacancy Pro") }) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Page d'inscription", fontSize = 30.sp)
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = registerViewModel.firstName,
                        onValueChange = {
                            input -> registerViewModel.updateFirstName(input)
                        },
                        label = {
                            Text("Prénom")
                        },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = registerViewModel.lastName,
                        onValueChange = {
                            input -> registerViewModel.updateLastName(input)
                        },
                        label = {
                            Text("Nom de famille")
                        },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = registerViewModel.email,
                        onValueChange = {
                            input -> registerViewModel.updateEmail(input)
                        },
                        label = {
                            Text("Email")
                        },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = registerViewModel.password,
                        onValueChange = {
                            input -> registerViewModel.updatePassword(input)
                        },
                        label = {
                            Text("Mot de passe")
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) }
                    )
                    OutlinedButton(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),onClick = { register(registerViewModel, navController) }) {
                        Text(text = "Créer un compte")
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = { navController.navigate(route = Screen.Login.route) }) {
                            Text(text = "Connecez vous")
                        }
                    }
                    Divider()
                }
            }
        }
    }
}
fun register(registerViewModel: RegisterViewModel, navController: NavController) {
    runBlocking { registerViewModel.register() }
    navController.navigate(route = Screen.Home.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}
@Composable
@Preview
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}