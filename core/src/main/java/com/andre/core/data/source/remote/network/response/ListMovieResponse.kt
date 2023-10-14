package com.andre.core.data.source.remote.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ListMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieResponse>
)
