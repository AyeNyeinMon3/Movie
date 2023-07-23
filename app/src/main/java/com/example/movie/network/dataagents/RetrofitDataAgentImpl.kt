package com.example.movie.network.dataagents

import com.example.movie.data.vos.ActorVO
import com.example.movie.data.vos.GenreVO
import com.example.movie.data.vos.MovieVO
import com.example.movie.network.TheMovieApi
import com.example.movie.network.responses.ActorListResponse
import com.example.movie.network.responses.CreditByMovieResponse
import com.example.movie.network.responses.GetGenreResponse
import com.example.movie.network.responses.MovieListResponse
import com.example.movie.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieDataAgent {

    private var mTheMovieApi : TheMovieApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)

    }


    override fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ) {

        mTheMovieApi?.getNowPlayingMovies()
            ?.enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {

                    if (response.isSuccessful){
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)

                    }else{
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

       mTheMovieApi?.getPopularMovies()
           ?.enqueue(object : Callback<MovieListResponse>{
               override fun onResponse(
                   call: Call<MovieListResponse>,
                   response: Response<MovieListResponse>
               ) {

                   if (response.isSuccessful){
                       val movieList = response.body()?.results ?: listOf()
                       onSuccess(movieList)

                   }else{
                       onFailure(response.message())
                   }

               }

               override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                   onFailure(t.message ?: "")
               }
           })

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {


        mTheMovieApi?.getTopRatedMovies()
            ?.enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful){
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)
                    }
                    else{
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {

                }

            })

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        mTheMovieApi?.getGenres()
            ?.enqueue(object : Callback<GetGenreResponse>{
                override fun onResponse(
                    call: Call<GetGenreResponse>,
                    response: Response<GetGenreResponse>
                ) {
                    if (response.isSuccessful){
                         onSuccess(response.body()?.genres ?: listOf())
                    }
                    else{
                        onFailure(response.message())                    }

                }

                override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })

    }

    override fun getMoviesByGenreId(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMoviesByGenreId(genreId = genreId)
            ?.enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful){
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)
                    }else{
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })

    }

    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getPopularActors()
            ?.enqueue(object : Callback<ActorListResponse>{
                override fun onResponse(
                    call: Call<ActorListResponse>,
                    response: Response<ActorListResponse>
                ) {
                    if(response.isSuccessful){
                        onSuccess(response.body()?.results?: listOf())
                    }
                    else{
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<ActorListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMovieDetails(movieId)
            ?.enqueue(object : Callback<MovieVO>{
                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                   onFailure(t.message ?: "")
                }

            })

    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCreditByMovie(movieId)
            ?.enqueue(object : Callback<CreditByMovieResponse>{
                override fun onResponse(
                    call: Call<CreditByMovieResponse>,
                    response: Response<CreditByMovieResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            onSuccess(Pair(it.cast ?: listOf(),it.crew ?: listOf()))
                        }
                    }else{
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<CreditByMovieResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })

    }
}
