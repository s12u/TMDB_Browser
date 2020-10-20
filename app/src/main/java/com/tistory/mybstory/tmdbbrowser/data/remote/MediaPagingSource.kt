package com.tistory.mybstory.tmdbbrowser.data.remote

import androidx.paging.PagingSource
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.model.Title
import java.lang.Exception

class MediaPagingSource constructor(
    private val movieRepository: MovieRepository,
    private val mediaType: MediaType
): PagingSource<Int, Title>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Title> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = movieRepository.getTrendingListByTypeForPaging(mediaType, page)

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
