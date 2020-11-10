package com.tistory.mybstory.tmdbbrowser.data.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tistory.mybstory.tmdbbrowser.data.remote.MediaPagingSource
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.repository.FakeMovieRepository
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.ui.main.MainViewModel
import com.tistory.mybstory.tmdbbrowser.util.CoroutineTestRule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule()

    val testDispatcher = TestCoroutineDispatcher()
    val testCoroutineScope = TestCoroutineScope(testDispatcher)

    lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = FakeMovieRepository()
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `test for getting trending movies flow success`() = runBlocking {
        // given
        val viewModel = MainViewModel(movieRepository, testDispatcher)
        // when
        viewModel.trendingMoviesFlow.collect {
            //then
            Assert.assertNotEquals(it, null)
        }
    }


}
