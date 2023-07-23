package com.example.movie.viewholders

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.MovieViewHolderDelegate
import com.example.movie.utils.IMAGE_BASE_URL

class MovieListViewHolder(itemView: View,private val delegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                delegate.onTapMovie(it.id)
            }

        }
    }

    fun bindData(movie:MovieVO){

        mMovieVO = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.findViewById(R.id.imageView))

        itemView.findViewById<TextView>(R.id.tv_movie_name).text = movie.title
        itemView.findViewById<TextView>(R.id.tv_movie_rating).text = movie.voteAverage?.toString()
        itemView.findViewById<RatingBar>(R.id.ratingBar).rating = movie.getRatingBaseOnFiveStars()

    }

}