package com.tistory.mybstory.tmdbbrowser.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.model.MovieImage

@JsonClass(generateAdapter = true)
data class ImageResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "backdrops")
    val backdrops: List<MovieImage>,
    @field:Json(name = "posters")
    val posters: List<MovieImage>
)
