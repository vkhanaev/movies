package com.example.movies.ui.main.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListBinding
import com.example.movies.domain.Movie
import com.example.movies.model.MovieSection
import com.example.movies.ui.main.details.OnItemViewClickListenerImpl
import com.example.movies.viewmodel.AppState
import java.lang.Thread.sleep

class MovieListFragment : Fragment() {
    private lateinit var viewModel: MovieListViewModel
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterNowPlaying: MovieListAdapter
    private lateinit var adapterUpcoming: MovieListAdapter
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавим разделитель карточек
        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.separator,null))

        adapterNowPlaying = MovieListAdapter(OnItemViewClickListenerImpl(activity?.supportFragmentManager, this ))
        with(binding) {
            movieListFragmentRecyclerView.adapter = adapterNowPlaying
            textViewNowPlaying.text = getString(R.string.now_playing)
            movieListFragmentRecyclerView.addItemDecoration(itemDecoration)
        }

        adapterUpcoming = MovieListAdapter(OnItemViewClickListenerImpl(activity?.supportFragmentManager, this ))
        with(binding) {
            movieListFragmentRecyclerViewUpcoming.adapter = adapterUpcoming
            textViewUpcoming.text = getString(R.string.upcoming)
            movieListFragmentRecyclerViewUpcoming.addItemDecoration(itemDecoration)
        }

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState>{
            override fun onChanged(appState: AppState) {
                renderData(appState)
            }
        })
        viewModel.getMovies(MovieSection.NowPlaying)
        sleep(1000)
        viewModel.getMovies(MovieSection.Upcoming)
    }

    fun renderData(appState: AppState) {
        when(appState) {
            is AppState.SuccessMulti -> {
                when(appState.movieSection) {
                    is MovieSection.NowPlaying -> {
                        adapterNowPlaying.setMovieList(appState.moviesData)
                    }
                    is MovieSection.Upcoming -> {
                        adapterUpcoming.setMovieList(appState.moviesData)
                    }
                }
            }

            is AppState.Loading -> {
                Toast.makeText(requireContext(), "Loading $appState", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error $appState", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}