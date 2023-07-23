package com.example.movie.network.responses

import com.example.movie.data.vos.GenreVO
import com.google.gson.annotations.SerializedName

data class GetGenreResponse (
    @SerializedName("genres")
    val genres : List<GenreVO>
        )