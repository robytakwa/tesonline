package com.arbaelbarca.listmovieskatalog.presenter.listmoviespresenter;

import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;
import com.arbaelbarca.listmovieskatalog.view.MainContract;

import java.util.ArrayList;

public class ListMoviePresenter implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {
    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public ListMoviePresenter(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }


    @Override
    public void requestFromDataServer() {
        getNoticeIntractor.getNoticeArrayList(this, "popular");
    }

    @Override
    public void refreshData(String type) {
        getNoticeIntractor.getNoticeArrayList(this, type);

    }


    @Override
    public void onFinished(ArrayList<ResultsItem> resultsItemArrayList) {
        if (mainView != null) {
            mainView.setDataToRecyclerView(resultsItemArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null)
            mainView.onResponseFailure(t);
    }
}
