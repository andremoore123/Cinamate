package com.andre.favorite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.cinamate.di.FavoriteModuleDependencies
import com.andre.cinamate.presentation.adapter.MovieAdapterHorizontal
import com.andre.cinamate.presentation.detail.DetailMovieActivity
import com.andre.core.domain.model.Movie
import com.andre.core.util.ObjectDataMapper
import com.andre.favorite.databinding.FragmentFavoriteBinding
import com.andre.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    @Inject lateinit var viewModel: FavoriteViewModel


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

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onAttach(context)
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