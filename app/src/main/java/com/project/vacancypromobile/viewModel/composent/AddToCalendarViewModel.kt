package com.project.vacancypromobile.viewModel.composent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Activity
import java.util.Calendar

class AddToCalendarViewModel ( private val activity : Activity): ViewModel()   {



    @SuppressLint("StaticFieldLeak")
    private var _context : Context? = null
    fun addToCalendar() {
        val beginTime : Calendar = activity.convertDateToCalendar( activity.beginDate)
        val endTime : Calendar = activity.convertDateToCalendar( activity.endDate)
        if(_context != null) {
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, activity.name)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, activity.place.name)
                .putExtra(CalendarContract.Events.DESCRIPTION, activity.description)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                .putExtra(CalendarContract.Events.ALL_DAY, true)
            startActivity(_context!!, intent, null)
        }
    }

    fun setContext(current: Context) {
        _context = current
    }


}