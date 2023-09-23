package com.andre.cinamate.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andre.core.domain.model.Movie
import com.andre.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    lateinit var isMovieFavorite: LiveData<Boolean>

    fun isMovieFavorite(movie: Movie) {
        isMovieFavorite = useCase.isMovieInFavorite(movie).asLiveData()
    }

    fun addToFavorite(movie: Movie) {
        useCase.setFavoriteMovie(movie, true)
    }

    fun removeFromFavorite(movie: Movie) {
        useCase.setFavoriteMovie(movie, false)
    }
}