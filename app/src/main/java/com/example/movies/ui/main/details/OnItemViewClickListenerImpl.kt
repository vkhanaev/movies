package com.example.movies.ui.main.details

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.movies.MainActivity
import com.example.movies.R
import com.example.movies.domain.Movie
import com.example.movies.ui.main.movielist.MovieListFragment

class OnItemViewClickListenerImpl(val fragmentManager: FragmentManager?, val fragment: MovieListFragment) :OnItemViewClickListener {
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