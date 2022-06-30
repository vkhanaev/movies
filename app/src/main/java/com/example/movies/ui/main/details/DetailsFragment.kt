package com.example.movies.ui.main.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies.R
import com.example.movies.databinding.DetailsFragmentBinding
import com.example.movies.domain.Movie

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle) : DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        if (movie!=null){
            renderMovie(movie)
        }

    }

    private fun renderMovie(movie: Movie) {
        binding.movieName.text = movie.name
        binding.movieNameEnglish.text = movie.nameEnglish
        binding.imageView.setImageResource(R.drawable.ic_launcher_background)
        binding.movieGenre.text = movie.genre
        binding.duration.text = "${movie.duration} min"
        binding.raiting.text = movie.raiting.toString()
        binding.budget.text = "budget: ${movie.budget} \$"
        binding.revenue.text = "revenue: ${movie.revenue} \$"
        binding.releaseDate.text = "releaseDate: ${movie.releaseDate}"
        binding.movieDescription.text = movie.description
    }
/*
    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.SuccessSingle -> {
                val movie = appState.movie
                binding.movieName.text = movie.name
                binding.movieNameEnglish.text = movie.nameEnglish
                binding.movieGenre.text = movie.genre
                binding.duration.text = "${movie.duration} min"
                binding.raiting.text = movie.raiting.toString()
                binding.budget.text = "budget: ${movie.budget} \$"
                binding.revenue.text = "revenue: ${movie.revenue} \$"
                binding.releaseDate.text = "releaseDate: ${movie.releaseDate}"
                binding.movieDescription.text = movie.description
            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(), "Loading $appState", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error $appState", Toast.LENGTH_SHORT).show()
            }
        }

    }
*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}