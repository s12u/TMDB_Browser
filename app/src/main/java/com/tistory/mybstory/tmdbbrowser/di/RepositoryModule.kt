package com.tistory.mybstory.tmdbbrowser.di

import com.squareup.moshi.Moshi
import com.tistory.mybstory.tmdbbrowser.data.remote.api.MovieApiService
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepository
import com.tistory.mybstory.tmdbbrowser.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(
        apiService: MovieApiService,
        jsonConverter: Moshi
    ): MovieRepository = MovieRepositoryImpl(apiService, jsonConverter)

}
