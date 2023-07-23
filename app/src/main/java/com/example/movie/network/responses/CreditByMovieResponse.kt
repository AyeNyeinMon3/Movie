package com.example.movie.network.responses

import com.example.movie.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class CreditByMovieResponse (

    @SerializedName("cast")
    val cast : List<ActorVO>,

    @SerializedName("crew")
    val crew : List<ActorVO>

        )