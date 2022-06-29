package com.example.movies.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.movies.R
import com.example.movies.databinding.MainFragmentBinding
import com.example.movies.viewmodel.AppState

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState>{
            override fun onChanged(appState: AppState?) {
                renderData(appState)
            }

        })

        viewModel.getMovies()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
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

}