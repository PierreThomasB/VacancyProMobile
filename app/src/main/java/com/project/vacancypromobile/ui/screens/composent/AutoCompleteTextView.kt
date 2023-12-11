
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

@Composable
fun PlacesAutocompleteTextField(
    modifier: Modifier = Modifier,
) {
    val autocompleteFragment  = AutocompleteSupportFragment()

    autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

    autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
        override fun onError(p0: Status) {
            TODO("Not yet implemented")
        }

        override fun onPlaceSelected(place: Place) {
        }


    })
    OutlinedTextField(
        modifier = modifier ,
        value = "Entrez l'endroit",
        onValueChange = { place -> run { autocompleteFragment.setText(place) } },
    )

}

@Preview
@Composable
fun PlacesAutocompleteTextFieldPreview() {
    PlacesAutocompleteTextField()
}