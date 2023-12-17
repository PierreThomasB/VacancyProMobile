package com.project.vacancypromobile.viewModel.composent

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel


class ShowItineraryViewModel (private val place : String ) : ViewModel( ) {


    fun showItinerary() {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(place)}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(context!!, mapIntent, null)
    }

    private var context: Context? = null


    fun setContext(context: Context) {
        this.context = context
    }
}