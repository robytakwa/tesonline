package com.arbaelbarca.listmovieskatalog.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arbaelbarca.listmovieskatalog.MainActivity
import com.arbaelbarca.listmovieskatalog.R
import com.arbaelbarca.listmovieskatalog.adapter.AdapterFavoriteMovies
import com.arbaelbarca.listmovieskatalog.baseactivity.BaseActivity
import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem
import com.arbaelbarca.listmovieskatalog.onclick.OnClickItem
import com.arbaelbarca.listmovieskatalog.utils.Constants
import kotlinx.android.synthetic.main.activity_favorite_movie.*
import kotlinx.android.synthetic.main.layout_not_found.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class FavoriteMovieActivity : BaseActivity() {

    private lateinit var adapterFavoriteMovies: AdapterFavoriteMovies
    private var resultsItems: List<ResultsItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init() {

        setToolbar(toolbar, "Favorite Movie")
        if (toolbar.navigationIcon != null) {
            toolbar.setNavigationOnClickListener { finish() }
        }
        adapterFavoriteMovies = AdapterFavoriteMovies(this@FavoriteMovieActivity)
        rvListFavoriteMovies.layoutManager = LinearLayoutManager(this)
        rvListFavoriteMovies.setHasFixedSize(true)
        rvListFavoriteMovies.adapter = adapterFavoriteMovies

        resultsItems = MainActivity.favoriteDatabase.favoriteDao().favoriteData

        if (resultsItems.isNotEmpty()) {
            progressBar.visibility = View.GONE
            adapterFavoriteMovies.setMovies(resultsItems)
            adapterFavoriteMovies.setOnClickItem(object : OnClickItem {
                override fun clickItemDetail(pos: Int) {
                    val intent = Intent(applicationContext, DetailMovieActivity::class.java)
                    intent.putExtra(Constants.DATA_ITEMS, resultsItems[pos])
                    startActivity(intent)
                }

            })
            adapterFavoriteMovies.notifyDataSetChanged()
        } else {
            progressBar.visibility = View.GONE
            llNotFound.visibility = View.VISIBLE
        }
    }
}
