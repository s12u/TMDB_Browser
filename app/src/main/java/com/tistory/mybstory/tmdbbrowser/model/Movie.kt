package com.tistory.mybstory.tmdbbrowser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.util.getPosterThumbUrl

@JsonClass(generateAdapter = true)
data class Movie constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPath: String? = null,
    @field:Json(name = "overview")
    val overview: String? = null
) {
    val posterUrl: String? get() = posterPath.getPosterThumbUrl()
}
