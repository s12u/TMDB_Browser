package com.tistory.mybstory.tmdbbrowser.base.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
abstract class BaseFragment: Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = viewLifecycleOwner.lifecycleScope.coroutineContext

}
