package com.example.movie.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.ShowcaseViewHolderDelegate
import com.example.movie.utils.BASE_URL
import com.example.movie.utils.IMAGE_BASE_URL

class ShowcasesViewHolder(itemView: View,private val delegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                delegate.onTapMovieFromShowcase(it.id)
            }

        }
    }

    fun bindData(movie : MovieVO){

        mMovieVO =movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.findViewById(R.id.iv_showcase_movie_image))

        itemView.findViewById<TextView>(R.id.tv_showCases_movieName).text = movie.title
        itemView.findViewById<TextView>(R.id.tv_showCases_date).text = movie.releaseDate
    }
}