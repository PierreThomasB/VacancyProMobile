package com.project.vacancypromobile.viewModel.composent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Activity

class AddToCalendarViewModel ( private val activity : Activity): ViewModel()   {



    @SuppressLint("StaticFieldLeak")
    private var _context : Context? = null
    fun addToCalendar() {
        if(_context != null) {
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, activity.name)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, activity.place.name)
                .putExtra(CalendarContract.Events.DESCRIPTION, activity.description)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, activity.beginDate)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, activity.endDate)
                .putExtra(CalendarContract.Events.ALL_DAY, true)
            startActivity(_context!!, intent, null)
        }
    }

    fun setContext(current: Context) {
        _context = current
    }


}