package com.andre.core.util

import androidx.recyclerview.widget.DiffUtil
import com.andre.core.domain.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }
}
