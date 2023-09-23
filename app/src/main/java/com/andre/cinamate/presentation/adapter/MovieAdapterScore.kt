package com.andre.cinamate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.cinamate.core.domain.model.Movie
import com.andre.cinamate.databinding.CardMovieScoreBinding
import com.andre.cinamate.util.MovieDiffCallback
import com.andre.cinamate.util.ObjectDataMapper

class MovieAdapterScore(private val clickEvent: ((Movie) -> Unit) = {}) :
    ListAdapter<Movie, MovieAdapterScore.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardMovieScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: CardMovieScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.moviePoster.load(ObjectDataMapper.IMAGE_BASE_LINK + movie.posterPath)
            binding.root.setOnClickListener {
                clickEvent(movie)
            }
        }
    }
}