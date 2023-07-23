package com.example.movie.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.adapter.MovieAdapter
import com.example.movie.data.vos.MovieVO
import com.example.movie.delegate.MovieViewHolderDelegate


class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    lateinit var movieAdapter: MovieAdapter
    lateinit var delegate: MovieViewHolderDelegate


    override fun onFinishInflate() {
        super.onFinishInflate()

//        setUpMovieListRecyclerView()

    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setUpDelegate(delegate)
        setUpMovieListRecyclerView()
    }

    private fun setUpDelegate(delegate: MovieViewHolderDelegate){
        this.delegate = delegate
    }

    private fun setUpMovieListRecyclerView() {
        movieAdapter = MovieAdapter(delegate)
        var movieList = findViewById<RecyclerView>(R.id.rv_movieList)
        movieList.adapter = movieAdapter
        movieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setData(movieList:List<MovieVO>){
        movieAdapter.setNewData(movieList)
    }

}