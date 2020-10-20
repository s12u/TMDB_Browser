package com.tistory.mybstory.tmdbbrowser.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MovieDetailViewModel @ViewModelInject constructor(
    @Assisted handle: SavedStateHandle
): ViewModel() {

    init {
        handle.keys().forEach {
            Timber.e("$it : ${handle.get<Any>(it)}")
        }

    }


}
