package com.tistory.mybstory.tmdbbrowser.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.model.Genre
import com.tistory.mybstory.tmdbbrowser.model.MovieImage

@JsonClass(generateAdapter = true)
data class MovieDetailResponse constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPath: String? = null,
    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,
    @field:Json(name = "overview")
    val overview: String? = null,
    @field:Json(name = "release_date")
    val releaseDate: String? = null,
    @field:Json(name = "images")
    val images: Map<String, List<MovieImage>>? = null,
    @field:Json(name = "genres")
    val genres: List<Genre>? = null
)
