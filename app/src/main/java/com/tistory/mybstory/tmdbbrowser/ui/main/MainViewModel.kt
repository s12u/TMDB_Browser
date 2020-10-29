package com.tistory.mybstory.tmdbbrowser.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tistory.mybstory.tmdbbrowser.data.remote.MediaPagingSource
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository

class MainViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val trendingMoviesFlow = Pager(
        PagingConfig(pageSize = 8, initialLoadSize = 10)
    ) {
        MediaPagingSource(
            movieRepository,
            MediaType.MOVIE(),
            MovieQueryType.Trending()
        )
    }.flow.cachedIn(viewModelScope)

    val popularMoviesFlow = Pager(
        PagingConfig(pageSize = 8, initialLoadSize = 10)
    ) {
        MediaPagingSource(
            movieRepository,
            MediaType.MOVIE(),
            MovieQueryType.Popular()
        )
    }.flow.cachedIn(viewModelScope)

    val topRatedMoviesFlow = Pager(
        PagingConfig(pageSize = 8, initialLoadSize = 10)
    ) {
        MediaPagingSource(
            movieRepository,
            MediaType.MOVIE(),
            MovieQueryType.TopRated()
        )
    }.flow.cachedIn(viewModelScope)
}
