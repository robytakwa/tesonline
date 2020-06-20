package com.arbaelbarca.listmovieskatalog.presenter.detailmoviespresenter;

import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.GenresItem;
import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.ResponseDetailMovies;
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResultsItemReviews;
import com.arbaelbarca.listmovieskatalog.view.DetailMoviesView;

import java.util.ArrayList;

public class DetailMoviesPresenter implements DetailMoviesView.presenter, DetailMoviesView.GetNoticeIntractorDetailMovies.OnFinishedListener {

    DetailMoviesView.presenter presenter;
    DetailMoviesView.MainView mainView;
    DetailMoviesView.GetNoticeIntractorDetailMovies getNoticeIntractorDetailMovies;


    public DetailMoviesPresenter(DetailMoviesView.MainView mainView, DetailMoviesView.GetNoticeIntractorDetailMovies getNoticeIntractorDetailMovies) {
        this.mainView = mainView;
        this.getNoticeIntractorDetailMovies = getNoticeIntractorDetailMovies;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void requestFromDataServer(String movieId) {
        getNoticeIntractorDetailMovies.getDetailReviews(this, movieId);
        getNoticeIntractorDetailMovies.getDetailMovies(this, movieId);
    }

    @Override
    public void refreshData(String type) {

    }

    @Override
    public void onFinished(ResponseDetailMovies responseDetailMovies) {
        if (mainView != null)
            mainView.setDataTags(responseDetailMovies);
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void setDataTags(ArrayList<GenresItem> genresItems) {

    }

    @Override
    public void setDataReview(ArrayList<ResultsItemReviews> resultsItemReviews) {
        if (mainView != null) {
            mainView.setDataReview(resultsItemReviews);
            mainView.hideProgress();
        }
    }
}
