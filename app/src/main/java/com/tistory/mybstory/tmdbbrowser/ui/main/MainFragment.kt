package com.tistory.mybstory.tmdbbrowser.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.base.ui.BaseFragment
import com.tistory.mybstory.tmdbbrowser.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with (binding) {
            viewModel = mainViewModel
            lifecycleOwner = viewLifecycleOwner
        }

    }
}
