package com.project.vacancypromobile.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import com.project.vacancypromobile.viewModel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    val loginMessage by loginViewModel.loginMessage.observeAsState()
    VacancypromobileTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        if (!loginMessage.isNullOrEmpty()) {
            LaunchedEffect(loginMessage) {
                snackbarHostState.showSnackbar(message = loginMessage!!, duration = SnackbarDuration.Short)
            }
        }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = { Text("Vacancy Pro") }) }
        ) {innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Column(modifier = Modifier.height(150.dp),verticalArrangement = Arrangement.Top) {
                        Row(modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(text = "Page de connexion", fontSize = 30.sp)
                        }
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
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
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                        ),
                        value = loginViewModel.password,
                        onValueChange = {
                            password -> loginViewModel.updatePassword(password)
                        },
                        label = {
                            Text("Mot de passe")
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null)}
                    )
                    OutlinedButton(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),onClick = { login(loginViewModel, navController) }) {
                        Text(text = "Se connecter")
                    }
                    Divider()
                }

            }
        }
    }
}

fun login(loginViewModel: LoginViewModel, navController: NavController) {
    runBlocking { loginViewModel.logIn() }
    if (loginViewModel.loginSuccess) {
        navController.navigate(route = Screen.Home.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}