package com.andre.cinamate.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.andre.cinamate.R
import com.andre.cinamate.databinding.ActivityDetailMovieBinding
import com.andre.core.domain.model.Movie
import com.andre.core.util.ObjectDataMapper.EXTRA_MOVIE
import com.andre.core.util.ObjectDataMapper.IMAGE_BASE_LINK
import com.andre.core.util.ObjectDataMapper.extractYear
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var movie: Movie
    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var favoriteButton: FloatingActionButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra(EXTRA_MOVIE)!!
        favoriteButton = binding.movieFavoriteButton

        viewModel.isMovieFavorite(movie)

        binding.imgDetailMovie.load(IMAGE_BASE_LINK + movie.backdropPath)
        binding.movieOrgTitle.text = movie.originalTitle
        binding.movieAltTitle.text = "${movie.title} (${extractYear(movie.releaseDate)})"
        binding.movieOverview.text = movie.overview
        binding.movieVoteAverage.text = movie.voteAverage.toString()


        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }

        viewModel.isMovieFavorite.observe(this) {
            setFavoriteButton(it)
        }

    }

    private fun setFavoriteButton(isFavorite: Boolean) {
        viewModel.isMovieFavorite(movie)
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
            favoriteButton.setOnClickListener {
                viewModel.removeFromFavorite(movie)
            }
        } else {
            favoriteButton.setImageResource(R.drawable.icon_favorite)
            favoriteButton.setOnClickListener {
                viewModel.addToFavorite(movie)
            }
        }
    }
}