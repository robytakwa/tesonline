package com.arbaelbarca.listmovieskatalog.presenter.detailmoviespresenter

import android.content.Context

import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.ResponseDetailMovies
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResponseReviewMovies
import com.arbaelbarca.listmovieskatalog.network.NetworkApi
import com.arbaelbarca.listmovieskatalog.utils.Constants
import com.arbaelbarca.listmovieskatalog.view.DetailMoviesView

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailMoviesPresenterImpl(private val context: Context) : DetailMoviesView.GetNoticeIntractorDetailMovies, DetailMoviesView.presenter {

    override fun getDetailMovies(onFinishedListener: DetailMoviesView.GetNoticeIntractorDetailMovies.OnFinishedListener, movie_id: String) {
        NetworkApi
                .instance
                .api
                .getDetailMovies(movie_id, Constants.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseDetailMovies>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(responseDetailMovies: ResponseDetailMovies?) {
                        if (responseDetailMovies != null) {
                            onFinishedListener.onFinished(responseDetailMovies)
                        }
                    }
                })
    }

    override fun getDetailReviews(onFinishedListener: DetailMoviesView.GetNoticeIntractorDetailMovies.OnFinishedListener, movie_id: String) {
        NetworkApi
                .instance
                .api
                .getDetailReviews(movie_id, Constants.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseReviewMovies>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(responseReviewMovies: ResponseReviewMovies) {
                        if (responseReviewMovies.results != null) {
                            onFinishedListener.setDataReview(responseReviewMovies.results)
                        }
                    }
                })
    }


    override fun onDestroy() {

    }

    override fun requestFromDataServer(movieId: String) {

    }


    override fun refreshData(type: String) {

    }
}
