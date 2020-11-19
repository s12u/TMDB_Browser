package com.tistory.mybstory.tmdbbrowser.data.ui.main

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieQueryType
import com.tistory.mybstory.tmdbbrowser.data.remote.api.response.MovieListResponse
import com.tistory.mybstory.tmdbbrowser.data.repository.FakeMovieRepository
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.ui.main.MainViewModel
import com.tistory.mybstory.tmdbbrowser.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test for getting trending movies flow success FROM fake repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = FakeMovieRepository()
            val viewModel = MainViewModel(movieRepository, testDispatcher)

            // when
            val result = viewModel.trendingMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }

    @Test
    fun `test for getting trending movies flow success FROM mock repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = mock(MovieRepository::class.java)
            val viewModel = MainViewModel(movieRepository, testDispatcher)
            val dummyData = FakeMovieRepository.dummyTrendingMovieList
            Mockito.`when`(movieRepository.getTrendingListByTypeForPaging(MediaType.MOVIE(), 1))
                .thenReturn(MovieListResponse(1, 1, dummyData))

            // when
            val result = viewModel.trendingMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }

    @Test
    fun `test for getting popular movies flow success FROM fake repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = FakeMovieRepository()
            val viewModel = MainViewModel(movieRepository, testDispatcher)

            // when
            val result = viewModel.popularMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }

    @Test
    fun `test for getting popular movies flow success FROM mock repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = mock(MovieRepository::class.java)
            val viewModel = MainViewModel(movieRepository, testDispatcher)
            val dummyData = FakeMovieRepository.dummyTrendingMovieList
            Mockito.`when`(movieRepository.getMoviesListByQueryTypeForPaging(MovieQueryType.Popular(), 1))
                .thenReturn(MovieListResponse(1, 1, dummyData))

            // when
            val result = viewModel.popularMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }

    @Test
    fun `test for getting top rated movies flow success FROM fake repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = FakeMovieRepository()
            val viewModel = MainViewModel(movieRepository, testDispatcher)

            // when
            val result = viewModel.topRatedMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }

    @Test
    fun `test for getting top rated movies flow success FROM mock repository`() =
        testCoroutineScope.runBlockingTest {
            // given
            val movieRepository = mock(MovieRepository::class.java)
            val viewModel = MainViewModel(movieRepository, testDispatcher)
            val dummyData = FakeMovieRepository.dummyTrendingMovieList
            Mockito.`when`(movieRepository.getMoviesListByQueryTypeForPaging(MovieQueryType.TopRated(), 1))
                .thenReturn(MovieListResponse(1, 1, dummyData))

            // when
            val result = viewModel.topRatedMoviesFlow.firstOrNull()

            // then
            Assert.assertNotNull(result)
        }



}
