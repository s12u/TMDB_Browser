package com.tistory.mybstory.tmdbbrowser.model.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType

@JsonClass(generateAdapter = true)
data class TrendingRequest(
    @field:Json(name = "api_key")
    val token: String,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "media_type")
    val mediaType: MediaType
)
