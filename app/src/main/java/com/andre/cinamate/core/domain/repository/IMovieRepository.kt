package com.andre.cinamate.core.domain.repository

import com.andre.cinamate.core.data.Resource
import com.andre.cinamate.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getListPopular(): Flow<Resource<List<Movie>>>

    fun getListUpComing(): Flow<Resource<List<Movie>>>

    fun getListTopRated(): Flow<Resource<List<Movie>>>

    fun getListNowPlaying(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun isMovieInFavorite(movie: Movie): Flow<Boolean>
}