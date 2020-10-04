package com.tistory.mybstory.tmdbbrowser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Title constructor(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPath: String? = null
)
