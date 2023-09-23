package com.andre.cinamate.core.data.source.remote.network

import com.andre.cinamate.core.data.source.remote.network.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    suspend fun getListPopular(): ListMovieResponse

    @GET("movie/now_playing")
    suspend fun getListNowPlaying(): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getListTopRated(): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getListUpComing(): ListMovieResponse
}