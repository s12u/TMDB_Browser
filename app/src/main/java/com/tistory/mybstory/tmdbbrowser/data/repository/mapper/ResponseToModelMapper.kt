package com.tistory.mybstory.tmdbbrowser.data.repository.mapper

import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieDetailResponse
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie

fun MovieListResponse.toMoviesList(): List<Movie> =
    this.results.map {
        Movie(
            id = it.id,
            title = it.title,
            posterPath = it.posterPath
        )
    }


fun MovieDetailResponse.toMovie(): Movie =
    Movie(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        overview = this.overview,
        releaseDate = this.releaseDate,
        posters = this.images?.get("posters") ?: listOf(),
        backdrops = this.images?.get("backdrops") ?: listOf(),
        genres = this.genres ?: listOf()
    )
