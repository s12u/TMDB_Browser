package com.tistory.mybstory.tmdbbrowser.data.remote.api

import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieDetailResponse
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
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
    ): Response<MovieListResponse>

    @GET("/3/movie/{query_type}")
    suspend fun getMovieListByQueryType(
        @Path("query_type") queryType: String,
        @Query("api_key") token: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") token: String,
        @Query("append_to_response") appendToResponse: String = "images"
    ): Response<MovieDetailResponse>

    @GET("/3/search/movie")
    suspend fun queryMovieByKeyword(
        @Query("api_key") token: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

}

sealed class MediaType {
    abstract val type: String
    class MOVIE(override val type: String = "movie") : MediaType()
    class TV(override val type: String = "tv") : MediaType()
}

sealed class MovieQueryType {
    abstract val type: String
    class Trending(override val type: String = "trending") : MovieQueryType()
    class Popular(override val type: String = "popular") : MovieQueryType()
    class TopRated(override val type: String = "top_rated") : MovieQueryType()
    class Query(override val type: String = "query") : MovieQueryType()
}
