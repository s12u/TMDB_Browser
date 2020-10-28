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

