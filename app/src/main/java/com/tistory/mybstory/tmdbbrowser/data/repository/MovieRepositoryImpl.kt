package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieDetailResponse
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.data.repository.mapper.toMovie
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository() {

    override suspend fun getMovieById(id: Int): Flow<Movie> = flow {
        val response: Response<MovieDetailResponse>
        try {
            response = apiService.getMovieById(id, AUTH_TOKEN)
        } catch (t: Throwable) {
            Timber.e("throwable : $t")
            throw Exception("Network error")
        }

        if (!response.isSuccessful) {
            throw Exception(response.errorBody().toString())
        } else {
            if (response.body() == null) {
                throw Exception("Body can't be null")
            } else {
                emit(response.body()!!.toMovie())
            }
        }
    }

    override suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int)
            : MovieListResponse? {
        val response: Response<MovieListResponse>
        try {
            response = apiService.getTrendingListDailyByType(
                mediaType.type,
                AUTH_TOKEN,
                page
            )
        } catch (t: Throwable) {
            throw Exception("Network error")
        }

        return if (!response.isSuccessful) {
            throw IllegalStateException(response.errorBody().toString())
        } else {
            response.body()
        }
    }

    override suspend fun getMoviesListByQueryTypeForPaging(
        movieQueryType: MovieQueryType,
        page: Int
    ): MovieListResponse? {
        val response: Response<MovieListResponse>
        try {
            response = apiService.getMovieListByQueryType(
                movieQueryType.type,
                AUTH_TOKEN,
                page
            )
        } catch (t: Throwable) {
            throw Exception("Network error")
        }

        return if (!response.isSuccessful) {
            throw IllegalStateException(response.errorBody().toString())
        } else {
            response.body()
        }
    }

    override suspend fun getMoviesListByKeywordForPaging(
        query: String,
        page: Int
    ): MovieListResponse? {
        val response: Response<MovieListResponse>
        try {
            response = apiService.queryMovieByKeyword(
                AUTH_TOKEN,
                query,
                page
            )
        } catch (t: Throwable) {
            throw Exception("Network error")
        }

        return if (!response.isSuccessful) {
            throw IllegalStateException(response.errorBody().toString())
        } else {
            response.body()
        }
    }

}
