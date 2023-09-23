package com.andre.cinamate.core.domain.usecase

import com.andre.cinamate.core.data.Resource
import com.andre.cinamate.core.domain.model.Movie
import com.andre.cinamate.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getListPopular(): Flow<Resource<List<Movie>>> = movieRepository.getListPopular()

    override fun getListUpComing(): Flow<Resource<List<Movie>>> = movieRepository.getListUpComing()

    override fun getListTopRated(): Flow<Resource<List<Movie>>> = movieRepository.getListTopRated()

    override fun getListNowPlaying(): Flow<Resource<List<Movie>>> =
        movieRepository.getListNowPlaying()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

    override fun isMovieInFavorite(movie: Movie): Flow<Boolean> =
        movieRepository.isMovieInFavorite(movie)

}