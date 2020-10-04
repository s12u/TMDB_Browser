package com.tistory.mybstory.tmdbbrowser.data.repository

import com.squareup.moshi.Moshi
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.model.api.request.TrendingRequest
import com.tistory.mybstory.tmdbbrowser.model.api.response.TrendingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val jsonConverter: Moshi
) : MovieRepository {

    override suspend fun getTrendingListByType(trendingRequest: TrendingRequest)
            : TrendingResponse? {
        Timber.e("before call! : Type: ${trendingRequest.mediaType.type} page: ${trendingRequest.page}")
        Timber.e(trendingRequest.token)

        val response = apiService.getTrendingListDailyByType(
            trendingRequest.mediaType.type,
            trendingRequest.token,
            trendingRequest.page
        )

        Timber.e("called!")
        if (response.isSuccessful) {
            Timber.e("success!")
            return response.body()
        } else {
            Timber.e("error!")
            throw Exception("network error")
        }
    }

}
