package com.andre.cinamate.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andre.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {
    val favoriteMovies = useCase.getFavoriteMovies().asLiveData()
}