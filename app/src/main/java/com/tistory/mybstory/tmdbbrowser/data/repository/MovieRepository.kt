package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.TrendingResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.MovieImage
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int): TrendingResponse?

    suspend fun getMovieById(id: Int): Flow<Movie>

    suspend fun getMovieImages(id: Int): Flow<Map<String, List<MovieImage>>>
}
