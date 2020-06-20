package com.arbaelbarca.listmovieskatalog.presenter.detailmoviespresenter;

import android.content.Context;

import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.ResponseDetailMovies;
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResponseReviewMovies;
import com.arbaelbarca.listmovieskatalog.network.NetworkApi;
import com.arbaelbarca.listmovieskatalog.view.DetailMoviesView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailMoviesPresenterImpl implements DetailMoviesView.GetNoticeIntractorDetailMovies, DetailMoviesView.presenter {

    private Context context;

    public DetailMoviesPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getDetailMovies(OnFinishedListener onFinishedListener, String movie_id) {
        NetworkApi
                .getInstance()
                .getAPI()
                .getDetailMovies(movie_id, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseDetailMovies>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseDetailMovies responseDetailMovies) {
                        if (responseDetailMovies != null) {
                            onFinishedListener.onFinished(responseDetailMovies);
                        }
                    }
                });
    }

    @Override
    public void getDetailReviews(OnFinishedListener onFinishedListener, String movie_id) {
        NetworkApi
                .getInstance()
                .getAPI()
                .getDetailReviews(movie_id, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseReviewMovies>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseReviewMovies responseReviewMovies) {
                        if (responseReviewMovies.getResults() != null) {
                            onFinishedListener.setDataReview(responseReviewMovies.getResults());
                        }
                    }
                });
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void requestFromDataServer(String movieId) {

    }


    @Override
    public void refreshData(String type) {

    }
}
