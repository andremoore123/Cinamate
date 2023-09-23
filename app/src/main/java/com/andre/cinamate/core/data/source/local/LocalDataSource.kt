package com.andre.cinamate.core.data.source.local

import com.andre.cinamate.core.data.source.local.entity.MovieEntity
import com.andre.cinamate.core.data.source.local.room.MovieDao
import com.andre.cinamate.util.ObjectDataMapper.LIST_POPULAR
import com.andre.cinamate.util.ObjectDataMapper.NOW_PLAYING
import com.andre.cinamate.util.ObjectDataMapper.TOP_RATED
import com.andre.cinamate.util.ObjectDataMapper.UP_COMING
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getTopRated(): Flow<List<MovieEntity>> = movieDao.getAllMovie(TOP_RATED)
    fun getUpComing(): Flow<List<MovieEntity>> = movieDao.getAllMovie(UP_COMING)
    fun getNowPlaying(): Flow<List<MovieEntity>> = movieDao.getAllMovie(NOW_PLAYING)
    fun getListPopular(): Flow<List<MovieEntity>> = movieDao.getAllMovie(LIST_POPULAR)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun isMovieInFavorite(id: Int): Flow<Boolean> = movieDao.isMovieInFavorite(id)
}