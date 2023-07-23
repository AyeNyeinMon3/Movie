package com.example.movie.network.responses

import com.example.movie.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class ActorListResponse (
    @SerializedName("page")
    val page : Int = 1,

    @SerializedName("results")
    val results : List<ActorVO>
        )