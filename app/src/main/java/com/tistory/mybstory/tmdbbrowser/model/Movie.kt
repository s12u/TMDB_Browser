package com.tistory.mybstory.tmdbbrowser.model

import com.squareup.moshi.JsonClass
import com.tistory.mybstory.tmdbbrowser.util.getPosterThumbUrl

@JsonClass(generateAdapter = true)
data class Movie constructor(
    val id: Int,
    val title: String,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val posters: List<MovieImage>? = null,
    val backdrops: List<MovieImage>? = null,
    val genres: List<Genre>? = null

) {
    val posterUrl: String? get() = posterPath.getPosterThumbUrl()
}
