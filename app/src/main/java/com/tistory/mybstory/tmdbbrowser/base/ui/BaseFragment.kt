package com.tistory.mybstory.tmdbbrowser.base.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<B : ViewDataBinding> constructor(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes), CoroutineScope {

    private lateinit var _binding: B
    protected val binding: B get() = _binding

    override val coroutineContext: CoroutineContext
        get() = viewLifecycleOwner.lifecycleScope.coroutineContext

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DataBindingUtil.bind(requireView())!!
        with (binding) {
            lifecycleOwner = viewLifecycleOwner
        }
    }

}
