package com.tistory.mybstory.tmdbbrowser.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.base.ui.BaseFragment
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.databinding.FragmentMainBinding
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.ui.detail.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var trendingMoviesAdapter: TrendingMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingMoviesAdapter = TrendingMoviesAdapter {
            // TODO: move to detail page
            handleItemClick(it)
        }

        with(binding) {
            viewModel = mainViewModel
            listTrendingMovies.adapter = trendingMoviesAdapter
        }

        launch {
            mainViewModel.trendingMoviesFlow.collectLatest { pagingData ->
                trendingMoviesAdapter.submitData(pagingData)
            }
        }
    }

    private fun handleItemClick(item: Title) =
        MainFragmentDirections.mainFragmentToMovieDetailFragment(item.id)
            .also { findNavController().navigate(it) }


}
