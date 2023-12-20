package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.viewModel.composent.ShowEditViewModel

@Composable
fun ShowEditComp(
    onConfirmed: () -> Unit = {},
    onDismissRequest: () -> Unit,
    showEditViewModel: ShowEditViewModel = viewModel(),
    beginDate: String,
    endDate: String,
) {

    AlertDialog(
        icon = {
            Icon(Icons.Default.Edit, contentDescription = "Modifier l'activité")
        },
        title = {
            Text(text = "Date de l'activité : ")
        },
        text = {
            Column(){
                Text(text = "Voulez vous changer les date de l'activité ?")
                DateRangeComp( selectStartDate = showEditViewModel::updatePeriodStartDate, selectEndDate = showEditViewModel::updatePeriodEndDate , beginDateStr = beginDate, endDateStr = endDate)
            }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmed()
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

@Composable
@Preview
fun ShowEditCompPreview() {
    ShowEditComp(
        onDismissRequest = {},
        beginDate = "",
        endDate = ""
    )
}