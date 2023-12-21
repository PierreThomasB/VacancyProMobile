import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.NewActivityViewModel
import com.project.vacancypromobile.viewModel.NewPeriodViewModel
import com.project.vacancypromobile.viewModel.PeriodDetailViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun PeriodPlacesAutocompleteTextField(
    viewModel: NewPeriodViewModel,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            label = { Text(text = "Lieu") },
            onValueChange = { newText ->
                text = newText
                isVisible = true
                viewModel.getPredictions(newText)
            })
        if (isVisible) {
            viewModel.predictions.observeAsState().value?.forEach { prediction ->
                val place = prediction.getFullText(null).toString()
                TextButton(onClick = {
                    viewModel.updatePeriodPlace(place, prediction.placeId)
                    text = viewModel.periodPlace
                    isVisible = false
                }) {
                    Text(place)
                }
            }
        }
    }
}

@Composable
fun ActivityPlacesAutocompleteTextField(
    viewModel: NewActivityViewModel,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            label = { Text(text = "Lieu") },
            onValueChange = { newText ->
                text = newText
                isVisible = true
                viewModel.getPredictions(newText)
            })
        if (isVisible) {
            viewModel.predictions.observeAsState().value?.forEach { prediction ->
                val place = prediction.getFullText(null).toString()
                TextButton(onClick = {
                    viewModel.updateActivityPlace(place, prediction.placeId)
                    text = viewModel.activityPlace
                    isVisible = false
                }) {
                    Text(place)
                }
            }
        }
    }
}

@Composable
fun UserAutocompleteTextField(
    viewModel: PeriodDetailViewModel,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }
    //var isVisible by remember { mutableStateOf(false) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier.onFocusChanged { focusState ->
                isDropdownExpanded = focusState.isFocused
            },
            value = text,
            label = { Text(text = "Utilisateur") },
            onValueChange = { newText ->
                text = newText
                //isVisible = true
                //viewModel.getPredictions(newText)
            })
        if (isDropdownExpanded) {
            viewModel.users.values.filter { it.username.contains(text, ignoreCase = true) }
                .forEach { user: User ->
                    TextButton(onClick = {
                        viewModel.updateUserToAdd(user.id)
                        text = user.username
                        isDropdownExpanded = false
                    }) {
                        Text(user.username)
                    }
                }
        }
    }
    Button(onClick = {
        runBlocking { viewModel.addUserToPeriod() }
        text = ""
    }) {
        Text(text = "Ajouter cet utilisateur")
    }
}

@Preview
@Composable
fun PlacesAutocompleteTextFieldPreview() {
    PeriodPlacesAutocompleteTextField(viewModel = viewModel())
}