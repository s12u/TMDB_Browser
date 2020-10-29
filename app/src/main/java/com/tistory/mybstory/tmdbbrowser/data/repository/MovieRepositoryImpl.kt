package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.MovieImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository {

    override suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int)
            : MovieListResponse? {

        val response = apiService.getTrendingListDailyByType(
            mediaType.type,
            AUTH_TOKEN,
            page
        )

        if (response.isSuccessful) {
            return response.body()
        } else {
            throw IllegalStateException("Api error")
        }
    }

    override suspend fun getMoviesListByQueryTypeForPaging(
        movieQueryType: MovieQueryType,
        page: Int
    ): MovieListResponse? {
        Timber.e("call by query type $movieQueryType")
        val response = apiService.getMovieListByQueryType(
            movieQueryType.type,
            AUTH_TOKEN,
            page
        )
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw IllegalStateException("Api error")
        }
    }

    override suspend fun getMovieById(id: Int): Flow<Movie> = flow {
        emit(apiService.getMovieById(id, AUTH_TOKEN))
    }

    override suspend fun getMovieImages(id: Int): Flow<Map<String, List<MovieImage>>> = flow {
        val response = apiService.getMovieImagesById(id, AUTH_TOKEN)
        if (response.isSuccessful) {
            response.body()?.let {
                Timber.e("backdrop count: ${it.backdrops.size} poster count : ${it.posters.size}")
                emit(
                    mapOf(
                        "backdrops" to it.backdrops,
                        "posters" to it.posters
                    )
                )
            }
        } else {
            throw IllegalStateException("Api error")
        }
    }

}
