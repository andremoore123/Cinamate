package com.andre.cinamate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andre.cinamate.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    val popularMovies = useCase.getListPopular().asLiveData()
    val topRatedMovies = useCase.getListTopRated().asLiveData()
    val upComingMovies = useCase.getListUpComing().asLiveData()
    val nowPlayingMovies = useCase.getListNowPlaying().asLiveData()
}