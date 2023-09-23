package com.andre.cinamate.util

import androidx.recyclerview.widget.DiffUtil
import com.andre.cinamate.core.domain.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.overview == newItem.overview
    }
}
