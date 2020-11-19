package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie
import kotlinx.coroutines.flow.Flow

abstract class MovieRepository {
    abstract suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int): MovieListResponse?

    abstract suspend fun getMoviesListByQueryTypeForPaging(movieQueryType: MovieQueryType, page: Int): MovieListResponse?

    abstract suspend fun getMoviesListByKeywordForPaging(query: String, page: Int): MovieListResponse?

    abstract suspend fun getMovieById(id: Int): Flow<Movie>
}
