package com.andre.cinamate.core.data.source.remote.network.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieResponse>
)
