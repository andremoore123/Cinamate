package com.andre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val originalTitle: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String,
    val category: String
) : Parcelable
