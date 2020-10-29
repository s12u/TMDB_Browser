package com.tistory.mybstory.tmdbbrowser.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.base.ui.BaseFragment
import com.tistory.mybstory.tmdbbrowser.databinding.FragmentMainBinding
import com.tistory.mybstory.tmdbbrowser.model.Title
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var trendingMoviesAdapter: HorizontalMoviesAdapter
    private lateinit var popularMoviesAdapter: HorizontalMoviesAdapter
    private lateinit var topRatedMoviesAdapter: HorizontalMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel = mainViewModel
        }
        initMoviesAdapters()
        observeTrendingMoviesListsFlow()
        observePopularMoviesListsFlow()
        observeTopRatedMoviesListsFlow()
    }

    private fun handleItemClick(item: Title) =
        MainFragmentDirections.mainFragmentToMovieDetailFragment(item.id)
            .also { findNavController().navigate(it) }


    private fun initMoviesAdapters() = with(binding) {
        // TODO: move to detail page
        trendingMoviesAdapter = HorizontalMoviesAdapter { handleItemClick(it) }
            .also { listTrendingMovies.adapter = it }
        popularMoviesAdapter = HorizontalMoviesAdapter { handleItemClick(it) }
            .also { listPopularMovies.adapter = it }
        topRatedMoviesAdapter = HorizontalMoviesAdapter { handleItemClick(it) }
            .also { listTopRatedMovies.adapter = it }
    }

    private fun observeTrendingMoviesListsFlow() = launch {
        mainViewModel.trendingMoviesFlow.collectLatest { pagingData ->
            trendingMoviesAdapter.submitData(pagingData)
        }
    }

    private fun observePopularMoviesListsFlow() = launch {
        mainViewModel.popularMoviesFlow.collectLatest { pagingData ->
            popularMoviesAdapter.submitData(pagingData)
        }
    }

    private fun observeTopRatedMoviesListsFlow() = launch {
        mainViewModel.topRatedMoviesFlow.collectLatest { pagingData ->
            topRatedMoviesAdapter.submitData(pagingData)
        }
    }
}
