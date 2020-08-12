package com.robytakwa.listpicturekatalog.presenter.detailmoviespresenter

import com.robytakwa.listpicturekatalog.model.modeldetailmovies.GenresItem
import com.robytakwa.listpicturekatalog.model.modeldetailmovies.ResponseDetailMovies
import com.robytakwa.listpicturekatalog.model.modelreviewmovies.ResultsItemReviews
import com.robytakwa.listpicturekatalog.view.DetailMoviesView
import java.util.*

class DetailMoviesPresenter(internal var mainView: DetailMoviesView.MainView?, internal var getNoticeIntractorDetailMovies: DetailMoviesView.GetNoticeIntractorDetailMovies) : DetailMoviesView.presenter, DetailMoviesView.GetNoticeIntractorDetailMovies.OnFinishedListener {

    internal var presenter: DetailMoviesView.presenter? = null

    override fun onDestroy() {
        mainView = null
    }

    override fun requestFromDataServer(movieId: String) {
        getNoticeIntractorDetailMovies.getDetailReviews(this, movieId)
        getNoticeIntractorDetailMovies.getDetailMovies(this, movieId)
    }

    override fun refreshData(type: String) {

    }

    override fun onFinished(responseDetailMovies: ResponseDetailMovies) {
        if (mainView != null)
            mainView!!.setDataTags(responseDetailMovies)
    }

    override fun onFailure(t: Throwable) {
        if (mainView != null) {
            mainView!!.onResponseFailure(t)
            mainView!!.hideProgress()
        }
    }

    override fun setDataTags(genresItems: ArrayList<GenresItem>) {

    }

    override fun setDataReview(resultsItemReviews: ArrayList<ResultsItemReviews>) {
        if (mainView != null) {
            mainView!!.setDataReview(resultsItemReviews)
            mainView!!.hideProgress()
        }
    }
}
