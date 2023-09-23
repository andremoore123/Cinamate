package com.andre.cinamate.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.cinamate.databinding.FragmentFavoriteBinding
import com.andre.cinamate.core.domain.model.Movie
import com.andre.cinamate.presentation.adapter.MovieAdapterHorizontal
import com.andre.cinamate.presentation.detail.DetailMovieActivity
import com.andre.cinamate.util.ObjectDataMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var rvFavoriteAdapter: MovieAdapterHorizontal

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val view = binding.root

        rvFavoriteAdapter = MovieAdapterHorizontal {
            onClickEvent(it)
        }

        binding.rvFavorite.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = rvFavoriteAdapter
        }
        return (view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            rvFavoriteAdapter.submitList(it)
            if (it.isEmpty()) {
                binding.emptyText.visibility = View.VISIBLE
            } else {
                binding.emptyText.visibility = View.GONE
            }
        }
    }

    private fun onClickEvent(movie: Movie) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(ObjectDataMapper.EXTRA_MOVIE, movie)
        )
    }
}