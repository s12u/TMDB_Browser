package com.tistory.mybstory.tmdbbrowser.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun String?.getPosterThumbUrl(): String {
    return if (!isNullOrEmpty()) {
        "https://image.tmdb.org/t/p/w500$this"
    } else ""
}

fun String?.getOriginalImageUrl(): String {
    return if (!isNullOrEmpty()) {
        "https://image.tmdb.org/t/p/original/$this"
    } else ""
}

fun String?.formatDateStringToLocalized(): String {
    return if (!isNullOrEmpty()) {
        val localDate = DateUtils.formatDateToLocalDate(this!!)
        DateUtils.formatLocalDateToString(localDate)
    } else ""
}

fun FragmentActivity.actionBarHeight(): Int {
    val tv = TypedValue()
    return if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    } else {
       48.dp
    }
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Fragment.hideKeyboard() {
    val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireView().rootView.windowToken, 0)
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
