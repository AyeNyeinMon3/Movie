package com.example.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.ShowcaseViewHolderDelegate
import com.example.movie.viewholders.ShowcasesViewHolder

class ShowcasesAdapter(val delegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowcasesViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcasesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcases,parent,false)
        return ShowcasesViewHolder(view,delegate)
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    override fun onBindViewHolder(holder: ShowcasesViewHolder, position: Int) {
        holder.bindData(mMovieList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}