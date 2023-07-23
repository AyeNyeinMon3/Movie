//package com.example.movie.network.dataagents
//
//
//import android.os.AsyncTask
//import com.example.movie.data.vos.MovieVO
//import com.example.movie.network.responses.MovieListResponse
//import com.example.movie.utils.API_GET_NOW_PLAYING
//import com.example.movie.utils.BASE_URL
//import com.example.movie.utils.MOVIE_API_KEY
//import com.google.gson.Gson
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.lang.Exception
//import java.util.concurrent.TimeUnit
//
//object OkHttpDataAgentImpl : MovieDataAgent {
//
//    private val mClient : OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(15,TimeUnit.SECONDS)
//        .readTimeout(15,TimeUnit.SECONDS)
//        .writeTimeout(15,TimeUnit.SECONDS)
//        .build()
//
//
//    class GetNowPlayingMovieOkHttpTask(
//        private val mOkHttpClient: OkHttpClient,
//    ): AsyncTask<Void,Void,MovieListResponse>(){
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val request = okhttp3.Request.Builder()
//                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
//                .build()
//
//            try {
//                val response = mOkHttpClient.newCall(request).execute()
//
//                if (response.isSuccessful){
//                    response.body?.let {
//                        val responseString = it.string()
//                        val response = Gson().fromJson<MovieListResponse>(
//                            responseString,
//                            MovieListResponse::class.java
//                        )
//                        return response
//                    }
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieOkHttpTask(mClient).execute()
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
//    }
//}