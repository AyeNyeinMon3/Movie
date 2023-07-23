package com.example.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.MovieViewHolderDelegate
import com.example.movie.viewholders.MovieListViewHolder

class MovieAdapter(val delegate: MovieViewHolderDelegate): RecyclerView.Adapter<MovieListViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
      val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_list,parent,false)
        return MovieListViewHolder(view,delegate)
    }

    override fun getItemCount(): Int {
       return mMovieList.count()
    }
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}