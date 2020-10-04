package com.tistory.mybstory.tmdbbrowser.data.remote

import androidx.paging.PagingSource
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.di.ApiModule.Companion.AUTH_TOKEN
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.model.api.request.TrendingRequest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MediaPagingSource constructor(
    val movieRepository: MovieRepository,
    val mediaType: MediaType
): PagingSource<Int, Title>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Title> {
        return try {
            Timber.e("load start")

            val page = params.key ?: STARTING_PAGE_INDEX
            val request = TrendingRequest(AUTH_TOKEN, page, mediaType)
            val response = movieRepository.getTrendingListByType(request)

            LoadResult.Page(
                data = response?.results ?: listOf(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response?.totalPages == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}
