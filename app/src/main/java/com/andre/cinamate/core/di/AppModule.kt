package com.andre.cinamate.core.di

import com.andre.cinamate.core.domain.usecase.MovieInteractor
import com.andre.cinamate.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(movieInteractor: MovieInteractor): MovieUseCase
}