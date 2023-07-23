package com.example.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.BannerViewHolderDelegate
import com.example.movie.viewholders.BannerViewHolder

class BannerAdapter(val delegate: BannerViewHolderDelegate): RecyclerView.Adapter<BannerViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner,parent,false)
        return BannerViewHolder(view,delegate)
    }

    override fun getItemCount(): Int {
        return if (mMovieList.count() > 5)
            5
        else
            mMovieList.count()
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList:List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}