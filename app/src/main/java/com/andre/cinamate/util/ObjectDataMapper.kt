package com.andre.cinamate.util

import com.andre.cinamate.core.data.source.local.entity.MovieEntity
import com.andre.cinamate.core.data.source.remote.network.response.MovieResponse
import com.andre.cinamate.core.domain.model.Movie
import java.util.regex.Pattern

object ObjectDataMapper {
    const val LIST_POPULAR = "listPopular"
    const val UP_COMING = "upComing"
    const val NOW_PLAYING = "nowPlaying"
    const val TOP_RATED = "topRated"
    const val IMAGE_BASE_LINK = "https://image.tmdb.org/t/p/original"
    const val EXTRA_MOVIE = "MOVIE"
    const val PREFERENCES_STORE_NAME = "dataStore"

    fun mapResponseToEntities(input: List<MovieResponse>, category: String): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                title = it.title,
                voteAverage = it.voteAverage,
                overview = it.overview,
                releaseDate = it.releaseDate,
                category = category
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                title = it.title,
                voteAverage = it.voteAverage,
                overview = it.overview,
                releaseDate = it.releaseDate
            )
        }

    fun mapDomainToEntities(input: Movie): MovieEntity =
        MovieEntity(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            originalTitle = input.originalTitle,
            title = input.title,
            voteAverage = input.voteAverage,
            overview = input.overview,
            releaseDate = input.releaseDate
        )

    fun extractYear(input: String): String {
        val pattern = Pattern.compile("(\\d{4})-\\d{2}-\\d{2}")
        val matcher = pattern.matcher(input)
        return if (matcher.find()) {
            matcher.group(1)!!
        } else {
            "YYYY"
        }
    }
}