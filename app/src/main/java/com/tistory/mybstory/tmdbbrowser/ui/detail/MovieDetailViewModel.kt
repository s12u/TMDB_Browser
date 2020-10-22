package com.tistory.mybstory.tmdbbrowser.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.model.Movie
import com.tistory.mybstory.tmdbbrowser.model.Title
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted handle: SavedStateHandle
) : ViewModel() {

    private val _movieLiveData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie> get() = _movieLiveData

    init {
        val id = handle.get<Int>("id") ?: -1
        if (id > 0) {
            fetchMovie(id)
        }
    }

    private fun fetchMovie(id: Int) {
        viewModelScope.launch {
            movieRepository.getMovieById(id).collect {
                _movieLiveData.postValue(it)
            }
        }
    }
}
