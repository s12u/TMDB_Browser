package com.tistory.mybstory.tmdbbrowser.util

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
