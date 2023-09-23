package com.example.movie.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie.R
import com.example.movie.adapter.MovieAdapter
import com.example.movie.data.models.MovieModel
import com.example.movie.data.models.MovieModelImpl
import com.example.movie.databinding.ActivityMovieSearchBinding
import com.example.movie.delegate.MovieViewHolderDelegate
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(),MovieViewHolderDelegate {

    lateinit var binding: ActivityMovieSearchBinding
    lateinit var mMovieAdapter: MovieAdapter

    private var mMovieModel : MovieModel = MovieModelImpl

    companion object{

        fun newIntent(context: Context) : Intent {
            val intent = Intent(context,MovieSearchActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpListeners()

    }

    @SuppressLint("CheckResult")
    private fun setUpListeners(){
        binding.edtSearch.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap {
                mMovieModel.getSearchMovie(it.toString())
            }//connect with observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                error(it.localizedMessage ?: "")
            })
    }

    private fun setUpRecyclerView(){
        mMovieAdapter = MovieAdapter(this)
        binding.rvSearchMovieList.adapter = mMovieAdapter
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }
}