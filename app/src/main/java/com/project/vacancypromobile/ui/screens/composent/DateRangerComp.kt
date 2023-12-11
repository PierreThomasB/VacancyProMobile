package com.project.vacancypromobile.ui.screens.composent

import android.icu.util.Calendar
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun DateRangeComp(
        modifier: Modifier = Modifier,
        selectStartDate: (String) -> Unit = {},
        selectEndDate: (String) -> Unit = {},
    ) {
        val calendar = Calendar.getInstance()
        calendar.set(2023, 11, 6) // year, month, date

        var startDate by remember {
            mutableLongStateOf(calendar.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
        }

        calendar.set(2023, 11, 6) // year, month, date

        var endDate by remember {
            mutableLongStateOf(calendar.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
        }

        // set the initial dates
        val dateRangePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = startDate,
            initialSelectedEndDateMillis = endDate
        )

        var showDateRangePicker by remember {
            mutableStateOf(false)
        }

        if (showDateRangePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDateRangePicker = false
                },
                confirmButton = {
                    TextButton(onClick = {

                        showDateRangePicker = false
                        val startDateTemp = dateRangePickerState.selectedStartDateMillis!!
                        val endDateTemp = dateRangePickerState.selectedEndDateMillis!!

                        val dateDebut = convertDate(startDateTemp)
                        val dateFin = convertDate(endDateTemp)

                        selectEndDate(dateDebut);
                        selectStartDate(dateFin);

                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDateRangePicker = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DateRangePicker(
                    state = dateRangePickerState,
                    modifier = Modifier.height(height = 500.dp) ,
                )
            }
        }

        Button(
            onClick = {
                showDateRangePicker = true
            },
            modifier = modifier,
        ) {
            Text(text = "Ajouter une période")
        }
    }


fun convertDate(dateInMillis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy");
    val calendar = Calendar.getInstance();
    calendar.timeInMillis = dateInMillis;
    return (formatter.format(Date(calendar.timeInMillis)));
}


@Composable
@Preview
fun DateRangerComp() {
    DateRangeComp()
}


