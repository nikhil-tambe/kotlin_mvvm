package com.nikhil.suven.views

import android.graphics.Typeface
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikhil.suven.R
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * */
@BindingAdapter("switchTextBold")
fun switchTextBold(textView: TextView, isFixed: Boolean) {
    if (isFixed) {
        textView.setTypeface(null, Typeface.BOLD)
    } else {
        textView.setTypeface(null, Typeface.NORMAL)
    }
}

/**
 * Convert millis to date and print
 */
@BindingAdapter("printDate")
fun printDate(view: View, timeInMillis: Long) {
    if (timeInMillis == 0L) {
        //view.text = ""
        if (view is EditText) view.hint = view.resources.getString(R.string.date_of_purchase)
    } else {
        val date = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            .format(Date(timeInMillis))
        printFormattedValue(view, date)
    }
}

/**
 * Convert millis to date and time and print
 */
@BindingAdapter("printDateTime")
fun printDateTime(view: View, timeInMillis: Long) {
    if (timeInMillis == 0L) {
        //view.text = ""
        if (view is EditText) view.hint = view.resources.getString(R.string.date_of_purchase)
    } else {
        val date = SimpleDateFormat("dd MMM yyyy, HH:mm:ss a", Locale.ENGLISH)
            .format(Date(timeInMillis))
        printFormattedValue(view, date)
    }
}

/**
 * Prints total number of entries (integer)
 */
@BindingAdapter("showCount")
fun showCount(view: TextView, count: Int) {
    if (count == 0) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
        view.text = count.toString()
    }
}

/**
 * Print int values in view
 * */
@BindingAdapter("printInt")
fun printInt(view: View, value: Int) {
    if (value == 0) {
        if (view is EditText) {
            view.setText("")
            return
        }
    }
    printFormattedValue(view, value.toString())
}

@BindingAdapter("printPrice")
fun printPrice(view: View, price: Float) {
    if (price == 0f) {
        if (view is EditText) {
            view.setText("")
            return
        }
    }
    val priceS = String.format(Locale.getDefault(), "%.2f", price)
    printFormattedValue(view, priceS)
}

@BindingAdapter("froWho")
fun froWho(view: View, value: Int) {
    val array = view.resources.getStringArray(R.array.for_who)
    if (value < array.size) {
        printFormattedValue(view, array[value])
    } else {
        printFormattedValue(view, "Invalid")
    }
}

fun printFormattedValue(view: View, value: String) {
    when (view) {
        is TextView -> view.text = value
        is EditText -> view.setText(value)
    }
}
