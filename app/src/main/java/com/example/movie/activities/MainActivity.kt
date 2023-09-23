
package com.example.movie.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.midi.MidiOutputPort
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.get
import com.example.movie.R
import com.example.movie.adapter.BannerAdapter
import com.example.movie.adapter.ShowcasesAdapter
import com.example.movie.data.models.MovieModel
import com.example.movie.data.models.MovieModelImpl
import com.example.movie.data.vos.GenreVO
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.delegate.BannerViewHolderDelegate
import com.example.movie.delegate.MovieViewHolderDelegate
import com.example.movie.delegate.ShowcaseViewHolderDelegate
import com.example.movie.viewpods.ActorListViewPod
import com.example.movie.viewpods.MovieListViewPod
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(),BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate{

    lateinit var binding: ActivityMainBinding
    lateinit var bannerAdapter: BannerAdapter
    lateinit var showcasesAdapter: ShowcasesAdapter

    lateinit var bestPopularMovieListViewPod: MovieListViewPod
    lateinit var genreMovieListViewPod: MovieListViewPod

    lateinit var bestActorListViewPod: ActorListViewPod

    //Model
    private val mMovieModel : MovieModel = MovieModelImpl

    //Data
    private var mGenres : List<GenreVO>? = null


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpBannerViewPager()
        //setUpGenreTabLayout()
        setUpListeners()
        setUpShowcasesAdapter()
        setUpViewPods()

        requestData()



    }

    @SuppressLint("SuspiciousIndentation")
    private fun requestData() {

        mMovieModel.getNowPlayingMovies {

        }?.observe(this) {
            bannerAdapter.setNewData(it)
        }

        mMovieModel.getPopularMovies{

        }?.observe(this){
            bestPopularMovieListViewPod.setData(it)
        }

        mMovieModel.getTopRatedMovies{

        }?.observe(this){
            showcasesAdapter.setNewData(it)
        }

        mMovieModel.getGenres(
            onSuccess = {
              mGenres = it
                setUpGenreTabLayout(it)

                //Get Movies by Genre for first genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }
            },
            onFailure = {
                error(it)
            }
        )

        mMovieModel.getPopularActors(
            onSuccess = {
                bestActorListViewPod = binding.vpBestActors.ActorListViewPod
                bestActorListViewPod.setData(it)
            },
            onFailure = {

            }
        )

    }

    private fun getMoviesByGenre(genreId : Int){
        mMovieModel.getMoviesByGenreId(genreId.toString(),
        onSuccess = {
            genreMovieListViewPod.setData(it)
        },
        onFailure = {

        })
    }

    private fun setUpViewPods(){

        bestPopularMovieListViewPod  = binding.vpBestPopularMovieList.movieListViewPod
        bestPopularMovieListViewPod.setUpMovieListViewPod(this)

        genreMovieListViewPod = binding.vpGenreMovieList.movieListViewPod
        genreMovieListViewPod.setUpMovieListViewPod(this)


    }

    private fun setUpShowcasesAdapter(){
        showcasesAdapter = ShowcasesAdapter(this)
        binding.rvShowCases.adapter = showcasesAdapter
    }

    private fun setUpListeners(){
        binding.tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Snackbar.make(window.decorView, tab?.text ?: "",Snackbar.LENGTH_LONG).show()
                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>){
//        dummyGenreList.forEach{
//                val tab = binding.tabLayoutGenre.newTab()
//                tab.text = it
//                binding.tabLayoutGenre.addTab(tab)
//            }

        genreList.forEach {
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)
            }
        }


    }

    private fun setUpBannerViewPager(){
        bannerAdapter = BannerAdapter(this)
        binding.viewPagerBanner.adapter = bannerAdapter


        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }

    private fun setUpToolBar() {
        //toolBar
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.search){
            startActivity(MovieSearchActivity.newIntent(this))

        }
        return true
    }

    override fun onTapMovieFromBanner(movieId : Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }
}