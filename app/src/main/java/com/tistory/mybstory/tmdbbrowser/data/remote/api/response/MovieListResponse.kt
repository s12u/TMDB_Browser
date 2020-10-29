package com.tistory.mybstory.tmdbbrowser.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.model.Title

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "total_pages")
    val totalPages: Int?,
    @field:Json(name = "results")
    val results: List<Title>
)
