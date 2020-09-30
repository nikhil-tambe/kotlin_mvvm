package com.nikhil.suven.views

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikhil.suven.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert millis to date and print
 */
@BindingAdapter("printDate")
fun printDate(view: TextView, timeInMillis: Long) {
    if (timeInMillis == 0L) {
        //view.text = ""
        view.hint = view.resources.getString(R.string.date_of_purchase)
    } else {
        val date = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            .format(Date(timeInMillis))
        view.text = date
    }
}