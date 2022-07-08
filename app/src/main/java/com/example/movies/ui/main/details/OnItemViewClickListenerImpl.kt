package com.example.movies.ui.main.details

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.movies.R
import com.example.movies.domain.Movie
import com.example.movies.ui.main.movielist.MovieListFragment

class OnItemViewClickListenerImpl(private val fragmentManager: FragmentManager?, private val fragment: MovieListFragment) :OnItemViewClickListener {
    override fun onItemViewClick(movie: Movie) {
        val manager = fragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
            manager.beginTransaction()
                .hide(fragment)
                .add(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}
