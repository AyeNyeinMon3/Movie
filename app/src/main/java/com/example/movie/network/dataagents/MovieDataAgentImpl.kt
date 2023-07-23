//package com.example.movie.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.example.movie.data.vos.MovieVO
//import com.example.movie.network.responses.MovieListResponse
//import com.example.movie.utils.API_GET_NOW_PLAYING
//import com.example.movie.utils.BASE_URL
//import com.example.movie.utils.MOVIE_API_KEY
//import com.google.gson.Gson
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.lang.Exception
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl  : MovieDataAgent {
//
//    class GetNowPlayingMoviesTask() : AsyncTask<Void,Void,MovieListResponse?>(){
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val url : URL
//            var reader : BufferedReader? = null
//            val stringBuilder : StringBuilder
//
//            try {
//                //create the HttpURLConnection
//                url = URL(""" $BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en_US&page=1""" )
//
//                val connection = url.openConnection() as HttpURLConnection
//
//                //set HTTP method
//                connection.requestMethod = "GET"
//
//                //give it 15s to respond
//                connection.readTimeout = 15 * 1000
//
//                connection.doInput = true
//                connection.doOutput = false
//
//                connection.connect()
//
//                //read the output from the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                )
//                stringBuilder = StringBuilder()
//
//                for (line in reader.readLines()){
//                    stringBuilder.append(line + "\n")
//                }
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMovies",responseString)
//
//                val movieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                )
//
//                return movieListResponse
//            }
//            catch (e:Exception){
//                e.printStackTrace()
//                Log.e("NewsError",e.message ?: "")
//            }
//            finally {
//                if (reader != null){
//                    try {
//                        reader.close()
//                    }catch (ioe : IOException){
//                        ioe.printStackTrace()
//                    }
//                }
//            }
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
//        GetNowPlayingMoviesTask().execute()
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
//
//}