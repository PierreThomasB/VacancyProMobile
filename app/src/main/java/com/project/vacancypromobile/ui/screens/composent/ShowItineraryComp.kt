package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.viewModel.composent.ShowItineraryViewModel

@Composable
fun ShowItineraryComp(showItineraryViewModel: ShowItineraryViewModel = viewModel()  , onDismissRequest: () -> Unit) {
    showItineraryViewModel.setContext(LocalContext.current);


    AlertDialog(
        icon = {
            Icon(Icons.Default.Place, contentDescription = "Itinéraire")
        },
        title = {
            Text(text = "Itinéraire")
        },
        text = {
            Text(text = "Voulez vous afficher l'itinéraire vers ce lieu")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showItineraryViewModel.showItinerary()
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