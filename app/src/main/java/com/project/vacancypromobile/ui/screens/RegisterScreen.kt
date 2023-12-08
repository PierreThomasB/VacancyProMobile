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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.vacancypromobile.ui.theme.VacancypromobileTheme
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    VacancypromobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Page d'inscription", fontSize = 30.sp)
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "Prénom"/*loginViewModel.email*/,
                        onValueChange = {
                            /*email -> loginViewModel.updateEmail(email)*/
                        },
                        label = {
                            Text("Prénom")
                        },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "Nom de famille"/*loginViewModel.email*/,
                        onValueChange = {
                            /*email -> loginViewModel.updateEmail(email)*/
                        },
                        label = {
                            Text("Nom de famille")
                        },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "Email"/*loginViewModel.email*/,
                        onValueChange = {
                            /*email -> loginViewModel.updateEmail(email)*/
                        },
                        label = {
                            Text("Email")
                        },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "Password"/*loginViewModel.password*/,
                        onValueChange = {
                            /*password -> loginViewModel.updatePassword(password)*/
                        },
                        label = {
                            Text("Mot de passe")
                        },
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) }
                    )
                    OutlinedButton(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),onClick = { runBlocking { /*loginViewModel.logIn()*/ }  }) {
                        Text(text = "Créer un compte")
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "Connecez vous")
                        }
                    }
                    Divider()
                }

            }
        }
    }
}
@Composable
@Preview
fun RegisterScreenPreview() {
    RegisterScreen()
}