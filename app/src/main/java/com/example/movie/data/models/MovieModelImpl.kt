package com.example.movie.data.models

import android.content.Context
import com.example.movie.data.vos.ActorVO
import com.example.movie.data.vos.GenreVO
import com.example.movie.data.vos.MovieVO
import com.example.movie.network.dataagents.MovieDataAgent
import com.example.movie.network.dataagents.RetrofitDataAgentImpl
import com.example.movie.persistence.MovieDatabase
import com.example.movie.utils.NOW_PLAYING
import com.example.movie.utils.POPULAR_MOVIES
import com.example.movie.utils.TOP_RATED_MOVIES

object MovieModelImpl : MovieModel {

    //Data Agent
     private val mMovieDataAgent : MovieDataAgent = RetrofitDataAgentImpl

    //Database
    private var mMovieDatabase : MovieDatabase? = null

    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Data Base
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        //Network
//        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess,onFailure = onFailure)
        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
            //database
            it.forEach{ movie -> movie.type = NOW_PLAYING }
            mMovieDatabase?.movieDao()?.insertMovies(it)
            //data layer
            onSuccess(it)
        },
        onFailure = onFailure)
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        //database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR_MOVIES) ?: listOf())

        //network
//        mMovieDataAgent.getPopularMovies(onSuccess = onSuccess,onFailure)
        mMovieDataAgent.getPopularMovies(onSuccess={
            //database
           it.forEach{ movie -> movie.type = POPULAR_MOVIES}
           mMovieDatabase?.movieDao()?.insertMovies(it)

            //data layer
            onSuccess(it)

        },
        onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED_MOVIES) ?: listOf())

        //network
//        mMovieDataAgent.getTopRatedMovies(onSuccess,onFailure)
        mMovieDataAgent.getTopRatedMovies(onSuccess = {
            it.forEach{ movie -> movie.type  }
            mMovieDatabase?.movieDao()?.insertMovies(it)

            onSuccess(it)
        },
        onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getGenres(onSuccess,onFailure)
    }

    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenreId(
            genreId,
            onSuccess,
            onFailure
        )

    }

    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getPopularActors(
            onSuccess,onFailure
        )
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //database
        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let{
            onSuccess(it)
        }
        //network
//        mMovieDataAgent.getMovieDetails(movieId,onSuccess,onFailure)
        mMovieDataAgent.getMovieDetails(movieId,
        onSuccess = {
            val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
            it.type = movieFromDatabase?.type
            mMovieDatabase?.movieDao()?.insertSingleMovie(it)

            onSuccess(it)
        },
        onFailure)
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditByMovie(
            movieId,
            onSuccess,
            onFailure
        )
    }
}