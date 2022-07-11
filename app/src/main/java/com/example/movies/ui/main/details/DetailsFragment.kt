package com.example.movies.ui.main.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.movies.R
import com.example.movies.databinding.DetailsFragmentBinding
import com.example.movies.domain.Movie
import com.example.movies.model.dto.MovieDTO
import com.example.movies.util.MovieLoader

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
    private lateinit var movieBundle: Movie

    private val onLoadListener: MovieLoader.MovieLoaderListener =
        object : MovieLoader.MovieLoaderListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                displayMovie(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {
                //Обработка ошибки
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Movie("zzz")
        //binding.mainView.visibility = View.GONE
        //binding.loadingLayout.visibility = View.VISIBLE
        val loader = MovieLoader(
            onLoadListener
        )
        loader.loadMovie()
    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        if (movie!=null){
            renderMovie(movie)
        }
    }
     */

    private fun displayMovie(movieDTO: MovieDTO) {
        /*with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.name
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            weatherCondition.text = translateCondition(weatherDTO.fact?.condition)
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()

             */

        Log.d("@@@", movieDTO.fact.toString())
            with(binding) {
                movieName.text = movieDTO.fact?.name
                movieNameEnglish.text = movieDTO.fact?.enName
                imageView.setImageResource(R.drawable.ic_launcher_background)
                movieGenre.text = movieDTO.fact?.type
                duration.text = "101 min"
                //raiting.text = movieDTO.fact?.raiting.toString()
                //budget.text = "budget: ${movieDTO.fact?.budget} \$"
                //revenue.text = "revenue: ${movieDTO.fact?.revenue} \$"
                raiting.text = 5.toString()
                budget.text = 5.toString()
                revenue.text = 5.toString()
                releaseDate.text = movieDTO.fact?.description
                movieDescription.text = movieDTO.fact?.shortDescription
        }
    }

    private fun renderMovie(movie: Movie) {
        with(binding) {
            movieName.text = movie.name
            movieNameEnglish.text = movie.nameEnglish
            imageView.setImageResource(R.drawable.ic_launcher_background)
            movieGenre.text = movie.genre
            duration.text = "${movie.duration} min"
            raiting.text = movie.raiting.toString()
            budget.text = "budget: ${movie.budget} \$"
            revenue.text = "revenue: ${movie.revenue} \$"
            releaseDate.text = "releaseDate: ${movie.releaseDate}"
            movieDescription.text = movie.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}