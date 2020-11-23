package com.tistory.mybstory.tmdbbrowser.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.base.ui.BaseFragment
import com.tistory.mybstory.tmdbbrowser.databinding.FragmentSearchMovieBinding
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.util.actionBarHeight
import com.tistory.mybstory.tmdbbrowser.util.hideKeyboard
import com.tistory.mybstory.tmdbbrowser.util.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieSearchFragment : BaseFragment<FragmentSearchMovieBinding>(
    R.layout.fragment_search_movie
) {
    private val vm: MovieSearchViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var movieSearchResultAdapter: MovieSearchResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel = vm
        }
        initUI()
        observeBottomSheetBehavior()
        observeTopRatedMoviesListsFlow()
    }

    private fun observeTopRatedMoviesListsFlow() = launch {
        vm.queryResultMoviesFlow.collectLatest { pagingData ->
            movieSearchResultAdapter.submitData(pagingData)
        }
    }

    private fun observeBottomSheetBehavior() = launch {
        vm.bottomSheetStateFlow.collect {
            bottomSheetBehavior.state = it
        }
    }

    private fun initUI() = with(binding) {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet as View).apply {
            peekHeight = requireActivity().actionBarHeight()
            this.isFitToContents = false
        }
        movieSearchResultAdapter = MovieSearchResultAdapter {
            handleItemClick(it)
        }.also {
            searchResult.adapter = it
        }
        binding.searchView.run {
            showKeyboard()
        }

    }

    private fun handleItemClick(item: Title) {
        hideKeyboard()
        val action = MovieSearchFragmentDirections.actionMoveToMovieDetailFragment(item.id)
        findNavController().navigate(action)
    }

}
