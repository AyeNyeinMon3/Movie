package com.example.movie.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.BannerViewHolderDelegate
import com.example.movie.utils.IMAGE_BASE_URL

class BannerViewHolder(itemView: View,private val delegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                delegate.onTapMovieFromBanner(it.id)
            }

        }
    }


    fun bindData(movie : MovieVO){

        mMovieVO = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.findViewById(R.id.iv_bannerImage))

        itemView.findViewById<TextView>(R.id.tv_bannerMovieName).text = movie.title
    }



}