abstract class PlaceAutocompleteViewModel : ViewModel() {
    val predictions = MutableLiveData<List<AutocompletePrediction>>()
    val place: String = ""

    fun getPredictions(query: String) {
        viewModelScope.launch {
            val placesClient = Places.createClient(application)
            val request = FindAutocompletePredictionsRequest.builder().setQuery(input).build()
            val response = placesClient.findAutocompletePredictions(request)
            response.addOnCompleteListener { task ->
                predictions.value = task.result.autocompletePredictions
            }
        }
    }

    abstract fun updatePlace(place: String, placeId; Int)
}