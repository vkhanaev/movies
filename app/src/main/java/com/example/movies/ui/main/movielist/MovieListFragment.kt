package com.example.movies.ui.main.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListBinding
import com.example.movies.domain.Movie
import com.example.movies.ui.main.details.DetailsFragment
import com.example.movies.ui.main.details.OnItemViewClickListener
import com.example.movies.viewmodel.AppState

class MovieListFragment : Fragment() {
    private lateinit var viewModel: MovieListViewModel
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieListAdapter(object : OnItemViewClickListener{
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if (manager!=null){
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                manager.beginTransaction()
                    .hide(this@MovieListFragment)
                    .add(R.id.container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

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