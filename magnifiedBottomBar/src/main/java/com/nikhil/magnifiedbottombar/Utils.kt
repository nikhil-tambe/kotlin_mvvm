package com.nikhil.magnifiedbottombar

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorInt

private var dp = 0f
private fun getDp(context: Context): Float {
    return if (dp == 0f) {
        context.resources.displayMetrics.density.apply {
            dp = this
        }
    } else
        dp
}

internal fun Float.dp(context: Context) = this * getDp(context)
internal fun Int.dp(context: Context) = this * getDp(context).toInt()

internal inline fun <T : View?> T.runAfterDelay(delay: Long, crossinline f: T.() -> Unit) {
    this?.postDelayed({
        try {
            f()
        } catch (e: Exception) {
        }
    }, delay)
}

internal fun ofColorStateList(
    @ColorInt color: Int
) = ColorStateList.valueOf(color)


fun <T> View?.updateLayoutParams(onLayoutChange: (params: T) -> Unit) {
    if (this == null)
        return
    try {
        @Suppress("UNCHECKED_CAST")
        onLayoutChange(layoutParams as T)
        layoutParams = layoutParams
    } catch (e: Exception) {
        e.printStackTrace()
    }
}