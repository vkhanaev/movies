package com.example.films.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.films.R
import com.example.films.databinding.MainActivityBinding
import com.example.films.databinding.MainFragmentBinding
import com.example.films.viewmodel.AppState

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
        //return inflater.inflate(R.layout.main_fragment, container, false)
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