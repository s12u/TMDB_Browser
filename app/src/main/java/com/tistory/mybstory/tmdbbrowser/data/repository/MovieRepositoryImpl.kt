package com.tistory.mybstory.tmdbbrowser.data.repository

import com.squareup.moshi.Moshi
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.TrendingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val jsonConverter: Moshi
) : MovieRepository {

    override suspend fun getTrendingListByTypeForPaging(mediaType: MediaType, page: Int)
            : TrendingResponse? {

        val response = apiService.getTrendingListDailyByType(
            mediaType.type,
            AUTH_TOKEN,
            page)

        Timber.e("called!")
        if (response.isSuccessful) {
            Timber.e("success!")
            return response.body()
        } else {
            Timber.e("error!")
            throw Exception("network error")
        }
    }

    override suspend fun getMovieById(id: Int): Flow<Title> = flow {
        emit(apiService.getMovieById(id))
    }

}
