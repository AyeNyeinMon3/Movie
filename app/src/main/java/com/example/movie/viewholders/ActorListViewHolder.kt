package com.example.movie.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.vos.ActorVO
import com.example.movie.utils.IMAGE_BASE_URL

class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindData(actor : ActorVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(itemView.findViewById(R.id.iv_actor))

        itemView.findViewById<TextView>(R.id.tv_actor_name).text = actor.name

    }
}