package com.tistory.mybstory.tmdbbrowser.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tistory.mybstory.tmdbbrowser.data.remote.MediaPagingSource
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MovieSearchViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _bottomSheetStateFlow = MutableStateFlow(BottomSheetBehavior.STATE_EXPANDED)
    val bottomSheetStateFlow: Flow<Int> get() = _bottomSheetStateFlow

    private val queryChannel = ConflatedBroadcastChannel("")
    private var pagingSource: MediaPagingSource? = null

    init {
        queryChannel.asFlow()
            .debounce(1000)
            .onEach { /* SEARCH FOR QUERY HERE */
                Timber.e("query: $it")
                pagingSource?.invalidate()
            }
            .launchIn(viewModelScope)
    }

    fun toggleBottomSheetState() {
        _bottomSheetStateFlow.value = when (_bottomSheetStateFlow.value) {
            BottomSheetBehavior.STATE_EXPANDED -> BottomSheetBehavior.STATE_HALF_EXPANDED
            BottomSheetBehavior.STATE_HALF_EXPANDED -> BottomSheetBehavior.STATE_EXPANDED
            else -> BottomSheetBehavior.STATE_HIDDEN
        }
        Timber.e("3: expanded 4: collapsed  / current: $bottomSheetStateFlow")
    }

    // TODO: handle error
    val queryResultMoviesFlow = Pager(
        PagingConfig(pageSize = 8, initialLoadSize = 10)
    ) {
        MediaPagingSource(
            movieRepository,
            MediaType.MOVIE(),
            MovieQueryType.Query(),
            queryChannel.value
        ).also {
            pagingSource = it
        }
    }.flow.catch { e ->
        handleError(e)
    }.cachedIn(viewModelScope)


    val queryByKeyword = fun(query: String?) {
        query?.let {
            queryChannel.offer(it)
        }
    }

    private fun handleError(e: Throwable) {
        when (e.message) {

        }
    }

}
