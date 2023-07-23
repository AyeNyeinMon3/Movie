package com.example.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.vos.ActorVO
import com.example.movie.viewholders.ActorListViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorListViewHolder>() {

    private var mActorList : List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor_list,parent,false)
        return ActorListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mActorList.count()
    }

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
       if (mActorList.isNotEmpty()){
           holder.bindData(mActorList[position])
       }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorList: List<ActorVO>){
        mActorList = actorList
        notifyDataSetChanged()
    }
}