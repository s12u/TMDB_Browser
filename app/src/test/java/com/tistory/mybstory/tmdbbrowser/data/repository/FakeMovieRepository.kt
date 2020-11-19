package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.Title
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class FakeMovieRepository : MovieRepository() {

    override suspend fun getTrendingListByTypeForPaging(
        mediaType: MediaType,
        page: Int
    ): MovieListResponse? {
        delay(1000)
        return MovieListResponse(1, 1, dummyTrendingMovieList)
    }

    override suspend fun getMoviesListByQueryTypeForPaging(
        movieQueryType: MovieQueryType,
        page: Int
    ): MovieListResponse? {
        delay(1000)
        return MovieListResponse(1, 1, dummyTrendingMovieList)
    }

    override suspend fun getMovieById(id: Int): Flow<Movie> {
        TODO("Not yet implemented")
    }

    companion object {
        val dummyTrendingMovieList = listOf(
            Title(0, "trending1", "path1"),
            Title(1, "trending2", "path2"),
            Title(2, "trending3", "path3"),
            Title(3, "trending4", "path4"))
     }

}