package com.andre.cinamate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.core.domain.model.Movie
import com.andre.cinamate.databinding.CardMovieNoScoreBinding
import com.andre.core.util.MovieDiffCallback
import com.andre.core.util.ObjectDataMapper.IMAGE_BASE_LINK

class MovieAdapterNoScore(private val clickEvent: ((Movie) -> Unit) = {}) :
    ListAdapter<Movie, MovieAdapterNoScore.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardMovieNoScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: CardMovieNoScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.moviePoster.load(IMAGE_BASE_LINK + movie.posterPath)
            binding.root.setOnClickListener {
                clickEvent(movie)
            }
        }
    }
}