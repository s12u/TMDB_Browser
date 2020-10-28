package com.tistory.mybstory.tmdbbrowser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.util.getOriginalImageUrl

@JsonClass(generateAdapter = true)
data class MovieImage constructor(
    @field:Json(name = "aspect_ratio")
    val aspectRatio: Float,
    @field:Json(name = "file_path")
    val filePath: String,
    @field:Json(name = "height")
    val height: String? = null,
    @field:Json(name = "width")
    val width: String? = null
) {
    val imageUrl: String? get() = filePath.getOriginalImageUrl()
}
