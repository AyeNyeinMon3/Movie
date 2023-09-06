package com.example.movie.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.vos.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies : List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie : MovieVO?)

    //read
    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId : Int) : LiveData<MovieVO?>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieByIdOneTime(movieId: Int) : MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type : String) : LiveData<List<MovieVO>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}