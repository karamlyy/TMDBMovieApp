package com.example.tmdbmovieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tmdbmovieapp.MainActivity
import com.example.tmdbmovieapp.R
import com.example.tmdbmovieapp.databinding.FragmentDetailBinding
import com.example.tmdbmovieapp.util.loadImage

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getMovieDetail(movieId = args.movieId)
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewErrorDetail.text = it ?: getString(R.string.error_loading_movie)
            binding.textViewErrorDetail.isVisible = true
        }
        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                it.backdropPath?.let { path ->
                    binding.imageViewDetail.loadImage(path)
                }

                binding.textViewDetailVote.text = it.voteAverage?.toString() ?: getString(R.string.not_available)
                binding.textViewDetailStudio.text = it.productionCompanies?.firstOrNull()?.name ?: getString(R.string.not_available)
                binding.textViewDetailLanguage.text = it.spokenLanguages?.firstOrNull()?.englishName ?: getString(R.string.not_available)
                binding.textViewDetailGenres.text = it.genres?.joinToString(", ") { genre -> genre?.name.toString() } ?: getString(R.string.not_available)
                binding.textViewDetailReleaseDate.text = it.releaseDate ?: getString(R.string.not_available)
                binding.textViewDetailRuntime.text = it.runtime?.let { runtime -> getString(R.string.runtime_format, runtime) } ?: getString(R.string.not_available)
                binding.textViewDetailHomepage.text = it.homepage ?: getString(R.string.not_available)

                binding.textViewDetailOverview.text = it.overview ?: getString(R.string.not_available)

                binding.movieDetailGroup.isVisible = true

                (requireActivity() as MainActivity).supportActionBar?.title = it.title ?: getString(R.string.not_available)
            } ?: run {
                binding.textViewErrorDetail.text = getString(R.string.error_loading_movie)
                binding.textViewErrorDetail.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
