package com.robytakwa.listpicturekatalog.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.robytakwa.listpicturekatalog.MainActivity
import com.robytakwa.listpicturekatalog.R
import com.robytakwa.listpicturekatalog.adapter.AdapterListReviewsMovies
import com.robytakwa.listpicturekatalog.adapter.AdapterTagsMovies
import com.robytakwa.listpicturekatalog.baseactivity.BaseActivity
import com.robytakwa.listpicturekatalog.model.modeldetailmovies.ResponseDetailMovies
import com.robytakwa.listpicturekatalog.model.modelreviewmovies.ResultsItemReviews
import com.robytakwa.listpicturekatalog.model.movies.ResultsItem
import com.robytakwa.listpicturekatalog.presenter.detailmoviespresenter.DetailMoviesPresenter
import com.robytakwa.listpicturekatalog.presenter.detailmoviespresenter.DetailMoviesPresenterImpl
import com.robytakwa.listpicturekatalog.utils.Constants
import com.robytakwa.listpicturekatalog.view.DetailMoviesView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movies.*
import kotlinx.android.synthetic.main.layout_not_found.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class DetailMovieActivity : BaseActivity(), DetailMoviesView.MainView {


    private var itemsItem: ResultsItem? = null

    private lateinit var detailMoviesPresenter: DetailMoviesPresenter
    private lateinit var adapterListReviewsMovies: AdapterListReviewsMovies

    private lateinit var adapterTagsMovies: AdapterTagsMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        initial()
    }

    @SuppressLint("CheckResult")
    private fun initial() {

        setToolbar(toolbar, "Detail Picture")
        if (toolbar.navigationIcon != null) {
            toolbar.setNavigationOnClickListener { finish() }
        }
        itemsItem = intent.getParcelableExtra(Constants.DATA_ITEMS)
        detailMoviesPresenter = DetailMoviesPresenter(this, DetailMoviesPresenterImpl(this@DetailMovieActivity))
        initAdapter()

        if (itemsItem != null) {
            tv_item_name.text = itemsItem!!.title

            tv_item_from.text = itemsItem!!.overview
            tv_date.text = itemsItem!!.releaseDate
            ratingMovies.rating = itemsItem?.voteAverage!!.toFloat()
            txtRatingBar.text = itemsItem?.voteAverage.toString()

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.noimage)
            requestOptions.error(R.drawable.noimage)

            img_item_photo.let {
                Glide.with(this)
                        .load(Constants.IMG_POSTER + itemsItem?.posterPath)
                        .apply(requestOptions)
                        .into(it)
            }

            val requestOptionsBackdrop = RequestOptions()
            requestOptionsBackdrop.placeholder(R.drawable.noimage)
            requestOptionsBackdrop.error(R.drawable.noimage)

            imgBackdrop.let {
                Glide.with(this)
                        .load(Constants.IMG_POSTER + itemsItem?.backdropPath)
                        .apply(requestOptionsBackdrop)
                        .into(it)
            }

            detailMoviesPresenter.requestFromDataServer(itemsItem!!.id.toString())

        }

        btnFloatFav?.setOnClickListener { getFavMovies(itemsItem!!) }

        statusFav()


    }

    private fun initAdapter() {
        adapterTagsMovies = AdapterTagsMovies(this)
        adapterListReviewsMovies = AdapterListReviewsMovies(this)

        rvReviewMovies.adapter = adapterListReviewsMovies
        rvReviewMovies.layoutManager = LinearLayoutManager(this)
        rvReviewMovies.setHasFixedSize(true)

        val mLayoutManagerTag = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        rvTags.adapter = adapterTagsMovies
        rvTags.layoutManager = mLayoutManagerTag
        rvTags.setHasFixedSize(true)

    }


    private fun getFavMovies(resultsItem: ResultsItem) {
        val favoriteList = ResultsItem()
        favoriteList.id = resultsItem.id
        favoriteList.title = resultsItem.title
        favoriteList.posterPath = resultsItem.posterPath
        favoriteList.releaseDate = resultsItem.releaseDate
        favoriteList.overview = resultsItem.overview
        favoriteList.backdropPath = resultsItem.backdropPath
        favoriteList.voteAverage = resultsItem.voteAverage

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(favoriteList.id) != 1) {
            imgLoveFav!!.setImageResource(R.drawable.ic_love_red)
            btnFloatFav!!.setImageResource(R.drawable.ic_love_red)
            MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList)
            Snackbar.make(rlDetail!!, "Tersimpan ke favorite", Snackbar.LENGTH_LONG).show()
        } else {
            imgLoveFav!!.setImageResource(R.drawable.ic_love_white)
            btnFloatFav!!.setImageResource(R.drawable.ic_love_white)
            MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList)
            Snackbar.make(rlDetail!!, "Batalkan Favorite", Snackbar.LENGTH_LONG).show()
        }
    }

    internal fun statusFav() {
        if (itemsItem != null)
            if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(itemsItem!!.id) == 1) {
                imgLoveFav?.setImageResource(R.drawable.ic_love_red)
                btnFloatFav?.setImageResource(R.drawable.ic_love_red)
            } else {
                imgLoveFav?.setImageResource(R.drawable.ic_love_white)
                btnFloatFav?.setImageResource(R.drawable.ic_love_white)
            }
    }

    override fun showProgress() {
        progressBarRevies?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBarRevies?.visibility = View.GONE
    }

    override fun setDataTags(responseDetailMovies: ResponseDetailMovies?) {
        if (responseDetailMovies != null) {
            adapterTagsMovies.setGenresItems(responseDetailMovies.genres!!)
            adapterTagsMovies.notifyDataSetChanged()

        }

    }

    override fun setDataReview(resultsItemArrayList: ArrayList<ResultsItemReviews>) {
        if (resultsItemArrayList.size > 0) {
            adapterListReviewsMovies.setReviewsArrayList(resultsItemArrayList)
            adapterListReviewsMovies.notifyDataSetChanged()
        } else {
            hideProgress()
            llNotFound.visibility = View.VISIBLE
        }
    }

    override fun onResponseFailure(throwable: Throwable) {
        detailMoviesPresenter.onFailure(throwable)
        hideProgress()
        showToast(getString(R.string.text_server_eror))

    }
}
