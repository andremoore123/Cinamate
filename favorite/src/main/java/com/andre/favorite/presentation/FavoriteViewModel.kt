package com.andre.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andre.core.domain.usecase.MovieInteractor
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val useCase: MovieInteractor
) : ViewModel() {
    val favoriteMovies = useCase.getFavoriteMovies().asLiveData()
}