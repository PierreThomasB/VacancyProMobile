package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.viewModel.composent.AddToCalendarViewModel

@Composable
fun AddToCalendarComp(addToCalendarViewModel: AddToCalendarViewModel = viewModel() , onDismissRequest: () -> Unit) {
    addToCalendarViewModel.setContext(LocalContext.current);
    AlertDialog(
        icon = {
            Icon(Icons.Default.DateRange, contentDescription = "Calendrier")
        },
        title = {
            Text(text = "Ajouter au calendrier")
        },
        text = {
            Text(text = "Voulez vous ajouter cette activité à votre calendrier ?")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    addToCalendarViewModel.addToCalendar()
                    onDismissRequest()
                }
            ) {
                Text("Confirmer")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Annuler")
            }
        }
    )
}