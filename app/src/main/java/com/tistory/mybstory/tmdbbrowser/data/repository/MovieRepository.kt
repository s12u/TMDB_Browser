package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.MovieImage
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int): MovieListResponse?

    suspend fun getMoviesListByQueryTypeForPaging(movieQueryType: MovieQueryType, page: Int): MovieListResponse?

    suspend fun getMovieById(id: Int): Flow<Movie>

    suspend fun getMovieImages(id: Int): Flow<Map<String, List<MovieImage>>>
}
