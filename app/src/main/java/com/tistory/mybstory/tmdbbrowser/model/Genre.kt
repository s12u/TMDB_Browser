package com.tistory.mybstory.tmdbbrowser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String
)