package com.example.movie.data.models

import android.annotation.SuppressLint
import android.content.Context
import com.example.movie.data.vos.ActorVO
import com.example.movie.data.vos.GenreVO
import com.example.movie.data.vos.MovieVO
import com.example.movie.network.dataagents.MovieDataAgent
import com.example.movie.persistence.MovieDatabase
import com.example.movie.utils.NOW_PLAYING
import com.example.movie.utils.POPULAR_MOVIES
import com.example.movie.utils.TOP_RATED_MOVIES
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : MovieModel,BaseModel() {

//    //Data Agent
//     private val mMovieDataAgent : MovieDataAgent = RetrofitDataAgentImpl

//    //Database
//    private var mMovieDatabase : MovieDatabase? = null

    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Data Base
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        //Network

//        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess,onFailure = onFailure)

//        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
//            //database
//            it.forEach{ movie -> movie.type = NOW_PLAYING }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//            //data layer
//            onSuccess(it)
//        },
//        onFailure = onFailure)
//    }

        mMovieApi.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                //on Next event
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

                onSuccess(it.results ?: listOf())
            }, {
                //on Error event
                onFailure(it.localizedMessage ?: "")
            })
        }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        //database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR_MOVIES) ?: listOf())

        //network
//        mMovieDataAgent.getPopularMovies(onSuccess = onSuccess,onFailure)

//        mMovieDataAgent.getPopularMovies(onSuccess={
//            //database
//           it.forEach{ movie -> movie.type = POPULAR_MOVIES}
//           mMovieDatabase?.movieDao()?.insertMovies(it)
//
//            //data layer
//            onSuccess(it)
//
//        },
//        onFailure = onFailure)
//    }

        mMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR_MOVIES }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

                onSuccess(it.results ?: listOf())

            },{
                onFailure(it.localizedMessage ?: "")
            })
            }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED_MOVIES) ?: listOf())

        //network
        mMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED_MOVIES }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

                onSuccess(it.results ?: listOf())

            },{
                onFailure(it.localizedMessage ?: "")

            })

    }

    @SuppressLint("CheckResult")
    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
       mMovieApi.getGenres()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               onSuccess(it.genres ?: listOf())
           },{
               onFailure(it.localizedMessage ?: "")
           })
    }

    @SuppressLint("CheckResult")
    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
     mMovieApi.getMoviesByGenreId(genreId)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe ({
             onSuccess(it.results ?: listOf())
         },{
             onFailure(it.localizedMessage ?: "")
         })

    }

    @SuppressLint("CheckResult")
    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getPopularActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
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
//        mMovieDataAgent.getMovieDetails(movieId,
//        onSuccess = {
//            val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//            it.type = movieFromDatabase?.type
//            mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//
//            onSuccess(it)
//        },
//        onFailure)

        mMovieApi.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type = movieFromDatabase?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

                onSuccess(it)
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       mMovieApi.getCreditByMovie(movieId)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               onSuccess(Pair(it.cast ?: listOf() , it.crew ?: listOf()))
           },{
               onFailure(it.localizedMessage ?: "")
           })
    }
}