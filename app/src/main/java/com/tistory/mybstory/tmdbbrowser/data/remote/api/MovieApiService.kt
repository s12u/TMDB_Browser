package com.tistory.mybstory.tmdbbrowser.data.remote.api

import com.tistory.mybstory.tmdbbrowser.model.api.response.TrendingResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("/3/trending/{type}/day")
    suspend fun getTrendingListDailyByType(
        @Path("type") titleType: String,
        @Query("api_key") token: String,
        @Query("page") page: Int
    ): Response<TrendingResponse>

}

sealed class MediaType {
    abstract val type: String
    class Movie(override val type: String = "movie") : MediaType()
    class TV(override val type: String = "tv") : MediaType()
}
