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

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
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

        viewModel.getFilms()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                Toast.makeText(requireContext(), "Success $appState", Toast.LENGTH_SHORT).show()
                binding.movieName.text = "Человек-паук: Через вселенные"
                binding.movieNameEnglish.text = "Spider-Man: Into the Spider-Verse (2018)"
                binding.movieType.text = "боевик, приключения, мультфильм"
                binding.duration.text = "117 min"
                binding.budget.text = "budget: 900 \$"
                binding.revenue.text = "revenue: 375 \$"
                binding.releaseDate.text = "releaseDate: 2018-12-06"
                binding.movieDescription.text = R.string.movieDescription.toString()

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