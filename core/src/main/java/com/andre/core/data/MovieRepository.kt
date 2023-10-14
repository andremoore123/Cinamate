package com.andre.core.data

import android.util.Log
import com.andre.core.data.source.local.LocalDataSource
import com.andre.core.data.source.remote.RemoteDataSource
import com.andre.core.data.source.remote.network.ApiResponse
import com.andre.core.data.source.remote.network.response.MovieResponse
import com.andre.core.domain.model.Movie
import com.andre.core.domain.repository.IMovieRepository
import com.andre.core.util.AppExecutors
import com.andre.core.util.ObjectDataMapper
import com.andre.core.util.ObjectDataMapper.LIST_POPULAR
import com.andre.core.util.ObjectDataMapper.NOW_PLAYING
import com.andre.core.util.ObjectDataMapper.TOP_RATED
import com.andre.core.util.ObjectDataMapper.UP_COMING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {
    private val appExecutors: AppExecutors = AppExecutors()

    override fun getListPopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListPopular().map {
                    ObjectDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListPopular()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = ObjectDataMapper.mapResponseToEntities(data, LIST_POPULAR)
                localDataSource.insertMovies(tourismList)
            }
        }.asFlow()


    override fun getListUpComing(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getUpComing().map {
                    Log.d("MovieRepository", it.toString())
                    ObjectDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListUpComing()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = ObjectDataMapper.mapResponseToEntities(data, UP_COMING)
                localDataSource.insertMovies(tourismList)
            }
        }.asFlow()

    override fun getListTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTopRated().map {
                    ObjectDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListTopRated()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = ObjectDataMapper.mapResponseToEntities(data, TOP_RATED)
                localDataSource.insertMovies(tourismList)
            }
        }.asFlow()

    override fun getListNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getNowPlaying().map {
                    ObjectDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListNowPlaying()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = ObjectDataMapper.mapResponseToEntities(data, NOW_PLAYING)
                localDataSource.insertMovies(tourismList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            ObjectDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = ObjectDataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun isMovieInFavorite(movie: Movie): Flow<Boolean> {
        return localDataSource.isMovieInFavorite(movie.id)
    }
}