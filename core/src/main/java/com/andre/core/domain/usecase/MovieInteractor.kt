package com.andre.core.domain.usecase

import com.andre.core.data.Resource
import com.andre.core.domain.model.Movie
import com.andre.core.domain.repository.IMovieRepository
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