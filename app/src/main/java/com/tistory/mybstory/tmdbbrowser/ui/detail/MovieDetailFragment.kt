package com.tistory.mybstory.tmdbbrowser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.base.ui.BaseFragment
import com.tistory.mybstory.tmdbbrowser.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail
) {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var backdropPagerAdapter: BackdropPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            viewModel = movieDetailViewModel
        }
        initBackdropPager()
    }

    private fun initBackdropPager() = with(binding) {
        backdropPagerAdapter = BackdropPagerAdapter()
        backdropPager.apply {
            adapter = backdropPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 3
            dotsIndicator.setViewPager2(this)
        }
    }

}
