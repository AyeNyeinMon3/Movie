package com.example.movie.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.adapter.ActorAdapter
import com.example.movie.data.vos.ActorVO

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var actorAdapter : ActorAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpActorRecyclerView()


    }

    fun setUpActorViewPod(backgroundColorReference: Int,titleText : String,moreTitleText : String){


        val tvMoreActors = findViewById<TextView>(R.id.tv_moreActors)

        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        findViewById<TextView>(R.id.tv_bestActors).text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }

    private fun setUpActorRecyclerView() {
        actorAdapter = ActorAdapter()
        findViewById<RecyclerView>(R.id.rv_actorList).adapter = actorAdapter
    }

    fun setData(actorList : List<ActorVO>){
       actorAdapter.setNewData(actorList)

    }

}