package com.andre.cinamate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.cinamate.databinding.CardMovieHorizontalBinding
import com.andre.core.domain.model.Movie
import com.andre.core.util.MovieDiffCallback
import com.andre.core.util.ObjectDataMapper

class MovieAdapterHorizontal(private val clickEvent: ((Movie) -> Unit) = {}) :
    ListAdapter<Movie, MovieAdapterHorizontal.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: CardMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieScore.text = movie.voteAverage.toString()
            binding.movieImage.load(ObjectDataMapper.IMAGE_BASE_LINK + movie.posterPath)
            binding.root.setOnClickListener {
                clickEvent(movie)
            }
        }
    }
}