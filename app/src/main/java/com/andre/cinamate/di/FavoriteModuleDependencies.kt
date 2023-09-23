package com.andre.cinamate.di

import com.andre.core.data.MovieRepository
import com.andre.core.domain.usecase.MovieInteractor
import com.andre.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun provideFavoriteUseCase(): MovieInteractor
}