package com.example.movie.activities
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.models.MovieModel
import com.example.movie.data.models.MovieModelImpl
import com.example.movie.data.vos.GenreVO
import com.example.movie.data.vos.MovieVO
import com.example.movie.databinding.ActivityMovieDetailsBinding
import com.example.movie.utils.IMAGE_BASE_URL
import com.example.movie.viewpods.ActorListViewPod


class MovieDetailsActivity:AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private  var mMovieModel : MovieModel = MovieModelImpl

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context,movieId : Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID,movieId)
            return intent
        }
    }

    //view pods
    private lateinit var actorViewPod: ActorListViewPod
    private lateinit var creatorViewPod: ActorListViewPod


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        // Set the status bar color to transparent
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        window.statusBarColor = Color.TRANSPARENT

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolBar)
        setContentView(binding.root)

        setUpViewPod()



       binding.btnBack.setOnClickListener {
         super.onBackPressed()
       }

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,550)
        movieId?.let {
            requestData(it)
        }


    }

    private fun requestData(movieId: Int){
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),

            onFailure = {

            }
        )?.observe(this){
            it?.let { movieDetails -> bindData(movieDetails) }
        }

        mMovieModel.getCreditByMovie(
            movieId = movieId.toString(),
            onSuccess = {

                actorViewPod.setData(it.first)
                creatorViewPod.setData(it.second)
            },
            onFailure = {

            }
        )
    }

    private fun bindData(movie : MovieVO){
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieImage)

        binding.collapsingToolBar.title = movie.title ?: ""

        binding.tvMovieName.text = movie.title ?: ""
        binding.tvMovieRating.text = movie.voteAverage?.toString()?.substring(0,3) ?: ""
        binding.ratingBar.rating = movie.getRatingBaseOnFiveStars()
        movie.voteCount?.let {
            binding.tvVotes.text = "$it VOTES"
        }
        binding.tvYear.text = movie.releaseDate?.substring(0,4)?: ""

        bindGenres(movie,movie.genres ?: listOf())

        binding.tvStoryLine.text = movie.overview ?: " "
        binding.tvOriginalTitle.text = movie.title ?: ""
        binding.tvType.text = movie.getGenresAsCommaSeparatedString()
        binding.tvProduction.text = movie.getProductionCompaniesAsCommaSeparatedString()
        binding.tvPremiere.text = movie.releaseDate ?: ""
        binding.tvDescription.text = movie.overview ?: ""

    }

    @SuppressLint("SuspiciousIndentation")
    private fun bindGenres(movie: MovieVO, genres : List<GenreVO>){
        movie.genres?.count()?.let {
            binding.chipFirst.text = genres.firstOrNull()?.name ?: ""
            binding.chipSecond.text= genres.getOrNull(1)?.name ?: ""

//            if (it<3){
//                binding.chipSecond.visibility = View.GONE
//
//            }else (it<1)
//                binding.chipFirst.visibility = View.GONE
        }
    }

    private fun setUpViewPod(){
        actorViewPod = binding.vpActorList.ActorListViewPod
        actorViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorViewPod = binding.vpCreatorList.ActorListViewPod
        creatorViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_creators),
            getString(R.string.lbl_more_creators)
        )
    }
}
