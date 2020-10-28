package com.tistory.mybstory.tmdbbrowser.data.repository

import com.squareup.moshi.Moshi
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.TrendingResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.MovieImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository {

    override suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int)
            : TrendingResponse? {

        val response = apiService.getTrendingListDailyByType(
            mediaType.type,
            AUTH_TOKEN,
            page)

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
                emit(mapOf("backdrops" to it.backdrops,
                    "posters" to it.posters)
                )
            }
        } else {
            throw IllegalStateException("Api error")
        }
    }

}
