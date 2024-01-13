package com.yigitkarakurt.tmdbapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yigitkarakurt.tmdbapp.MainActivity
import com.yigitkarakurt.tmdbapp.R
import com.yigitkarakurt.tmdbapp.databinding.FragmentDetailBinding
import com.yigitkarakurt.tmdbapp.util.loadImage


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getMovieDetail(movideId = args.movieId)
        observeEvents()
        return binding.root
    }

    private fun observeEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewDetailError.text = it
            binding.textViewDetailError.isVisible = true
        }

        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            binding.imageViewDetail.loadImage(movie.backdropPath)

            binding.textViewDetailVote.text = movie.voteAverage.toString()
            binding.textViewDetailStudio.text = movie.productionCompanies?.first()?.name
            binding.textViewDetailLang.text = movie.spokenLanguages?.first()?.englishName

            binding.textViewDetailOverview.text = movie.overview

            binding.movieDetailGroup.isVisible = true

            (requireActivity() as MainActivity).supportActionBar?.title = movie.title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}