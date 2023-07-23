package com.example.movie

import android.app.Application
import com.example.movie.data.models.MovieModelImpl

class MovieApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}