package com.andre.cinamate.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andre.cinamate.databinding.FragmentHomeBinding
import com.andre.cinamate.presentation.adapter.MovieAdapterNoScore
import com.andre.cinamate.presentation.adapter.MovieAdapterScore
import com.andre.cinamate.presentation.detail.DetailMovieActivity
import com.andre.core.data.Resource
import com.andre.core.domain.model.Movie
import com.andre.core.util.ObjectDataMapper.EXTRA_MOVIE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var upComingAdapter: MovieAdapterNoScore
    private lateinit var nowPlayingAdapter: MovieAdapterNoScore
    private lateinit var mostPopularAdapter: MovieAdapterNoScore
    private lateinit var topRatedAdapter: MovieAdapterScore

    private lateinit var loadingBar: ProgressBar

    private var upComingLinear = true
    private var topRatedLinear = true
    private var nowPlayingLinear = true
    private var mostPopularLinear = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        loadingBar = binding.progressBar

        upComingAdapter = MovieAdapterNoScore {
            onClickEvent(it)
        }

        nowPlayingAdapter = MovieAdapterNoScore {
            onClickEvent(it)
        }

        mostPopularAdapter = MovieAdapterNoScore {
            onClickEvent(it)
        }

        topRatedAdapter = MovieAdapterScore {
            onClickEvent(it)
        }

        binding.rvNowPlaying.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = upComingAdapter
        }
        binding.rvTopRated.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = topRatedAdapter
        }
        binding.rvListPopular.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = mostPopularAdapter
        }
        binding.rvUpComing.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = upComingAdapter
        }

        binding.seeMoreUpComing.setOnClickListener {
            setLayoutRecyclerView(upComingLinear, binding.rvUpComing)
            upComingLinear = !upComingLinear
        }

        binding.seeMoreTopRated.setOnClickListener {
            setLayoutRecyclerView(topRatedLinear, binding.rvTopRated)
            topRatedLinear = !topRatedLinear
        }

        binding.seeMoreNowPlaying.setOnClickListener {
            setLayoutRecyclerView(nowPlayingLinear, binding.rvNowPlaying)
            nowPlayingLinear = !nowPlayingLinear
        }

        binding.seeMoreListPopular.setOnClickListener {
            setLayoutRecyclerView(mostPopularLinear, binding.rvListPopular)
            mostPopularLinear = !mostPopularLinear
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.upComingMovies.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingBar(true)
                    }

                    is Resource.Success -> {
                        showLoadingBar(false)
                        upComingAdapter.submitList(it.data)
                    }

                    is Resource.Error -> {
                        Log.e("HomeViewModel", it.message.toString())
                    }
                }
            }
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingBar(true)
                    }

                    is Resource.Success -> {
                        showLoadingBar(false)
                        topRatedAdapter.submitList(it.data)
                    }

                    is Resource.Error -> {
                        Log.e("HomeViewModel", it.message.toString())
                    }
                }
            }
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingBar(true)
                    }

                    is Resource.Success -> {
                        showLoadingBar(false)
                        mostPopularAdapter.submitList(it.data)
                    }

                    is Resource.Error -> {
                        Log.e("HomeViewModel", it.message.toString())
                    }
                }
            }
        }
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingBar(true)
                    }

                    is Resource.Success -> {
                        showLoadingBar(false)
                        nowPlayingAdapter.submitList(it.data)
                    }

                    is Resource.Error -> {
                        Log.e("HomeViewModel", it.message.toString())
                    }
                }
            }
        }
    }

    private fun onClickEvent(movie: Movie) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(EXTRA_MOVIE, movie)
        )
    }

    private fun setLayoutRecyclerView(isLinear: Boolean, recyclerView: RecyclerView) {
        if (isLinear) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showLoadingBar(isLoading: Boolean) {
        if (isLoading) loadingBar.visibility = View.VISIBLE else loadingBar.visibility = View.GONE
    }
}