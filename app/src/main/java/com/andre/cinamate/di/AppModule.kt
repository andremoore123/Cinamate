package com.andre.cinamate.di

import com.andre.core.domain.usecase.MovieInteractor
import com.andre.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}