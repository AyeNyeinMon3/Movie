package com.example.movie.network

import com.example.movie.data.vos.MovieVO
import com.example.movie.network.responses.ActorListResponse
import com.example.movie.network.responses.CreditByMovieResponse
import com.example.movie.network.responses.GetGenreResponse
import com.example.movie.network.responses.MovieListResponse
import com.example.movie.utils.API_CREDIT_BY_MOVIE
import com.example.movie.utils.API_GENRE_LIST
import com.example.movie.utils.API_GET_NOW_PLAYING
import com.example.movie.utils.API_GET_POPULAR
import com.example.movie.utils.API_GET_TOP_RATED
import com.example.movie.utils.API_MOVIES_BY_GENRE_ID
import com.example.movie.utils.API_MOVIE_DETAILS
import com.example.movie.utils.API_POPULAR_ACTORS
import com.example.movie.utils.MOVIE_API_KEY
import com.example.movie.utils.PARAM_API_KEY
import com.example.movie.utils.PARAM_GENRE_ID
import com.example.movie.utils.PARAM_PAGE
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1,

    ) : Observable<MovieListResponse>

    @GET(API_GET_POPULAR)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GENRE_LIST)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,

    ) : Observable<GetGenreResponse>

    @GET(API_MOVIES_BY_GENRE_ID)
    fun getMoviesByGenreId(
        @Query(PARAM_GENRE_ID) genreId : String,
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,

    ) : Observable<MovieListResponse>

    @GET(API_POPULAR_ACTORS)
    fun getPopularActors(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ) : Observable<ActorListResponse>

    @GET("$API_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey :String = MOVIE_API_KEY,

    ) : Observable<MovieVO>

    @GET("$API_CREDIT_BY_MOVIE/{movie_id}/credits")
    fun getCreditByMovie(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ) : Observable<CreditByMovieResponse>

}