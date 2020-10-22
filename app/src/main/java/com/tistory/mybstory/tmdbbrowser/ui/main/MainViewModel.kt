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
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository

class MainViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val trendingMoviesFlow = Pager(
        PagingConfig(pageSize = 8, initialLoadSize = 10)
    ) { MediaPagingSource(movieRepository, MediaType.MOVIE()) }
        .flow
        .cachedIn(viewModelScope)
}
