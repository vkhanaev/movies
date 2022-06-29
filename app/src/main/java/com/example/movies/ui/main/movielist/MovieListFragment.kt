package com.example.movies.ui.main.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListBinding
import com.example.movies.viewmodel.AppState

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieListFragment : Fragment() {
    private lateinit var viewModel: MovieListViewModel
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieListAdapter()

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }


    }
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieListFragmentRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState>{
            override fun onChanged(appState: AppState) {
                renderData(appState)
            }
        })

        viewModel.getMovies()

    }

    fun renderData(appState: AppState) {
        when(appState) {
            is AppState.SuccessMulti -> {
                adapter.setMovie(appState.moviesData)
                //Toast.makeText(context, "toast ${appState.moviesData}",Toast.LENGTH_LONG).show()
            }
        }

    }
    companion object {
        fun newInstance() = MovieListFragment()
            /*.apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }

             */
    }
}