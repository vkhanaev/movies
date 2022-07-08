package com.example.movies.ui.main.movielist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListRecyclerItemBinding
import com.example.movies.domain.Movie
import com.example.movies.ui.main.details.OnItemViewClickListener
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class MovieListAdapter(
    private val onItemViewClickListener: OnItemViewClickListener?) : RecyclerView.Adapter<MovieListAdapter.MainViewHolder>() {

    private var moviesData: List<Movie> = listOf()

    fun setMovieList(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = FragmentMovieListRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
        /*
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_movie_list_recycler_item, parent, false)
                    as View
        )

         */
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    //inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
    inner class MainViewHolder(binding: FragmentMovieListRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            itemView.findViewById<ImageView>(R.id.movieImage).setImageResource(R.drawable.ic_launcher_background)
            itemView.findViewById<TextView>(R.id.movieNameRecycleItemTextView).text = movie.name
            itemView.findViewById<TextView>(R.id.year).text = movie.releaseDate
            itemView.findViewById<TextView>(R.id.raiting).text = movie.raiting.toString()

            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movie)
            }
        }
    }
}
