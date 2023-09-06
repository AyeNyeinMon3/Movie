package com.example.movie.data.models

import androidx.lifecycle.LiveData
import com.example.movie.data.vos.ActorVO
import com.example.movie.data.vos.GenreVO
import com.example.movie.data.vos.MovieVO

interface MovieModel {

    fun getNowPlayingMovies(
//        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit

    ):LiveData<List<MovieVO>>?

    fun getPopularMovies(

        onFailure: (String) -> Unit
    ):LiveData<List<MovieVO>>?

    fun getTopRatedMovies(

        onFailure: (String) -> Unit
    ):LiveData<List<MovieVO>>?

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenreId(
        genreId : String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId : String,
        onFailure: (String) -> Unit

    ):LiveData<MovieVO?>?

    fun getCreditByMovie(
        movieId: String,
        onSuccess : (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

}