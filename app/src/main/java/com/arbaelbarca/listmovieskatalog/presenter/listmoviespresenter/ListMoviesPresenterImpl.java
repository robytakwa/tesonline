package com.arbaelbarca.listmovieskatalog.presenter.listmoviespresenter;

import android.content.Context;
import android.util.Log;

import com.arbaelbarca.listmovieskatalog.model.movies.ResponseMoviesList;
import com.arbaelbarca.listmovieskatalog.network.NetworkApi;
import com.arbaelbarca.listmovieskatalog.view.MainContract;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListMoviesPresenterImpl implements MainContract.GetNoticeIntractor, MainContract.presenter {

    private Context context;

    public ListMoviesPresenterImpl(Context context) {
        this.context = context;
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void requestFromDataServer() {

    }

    @Override
    public void refreshData(String textType) {
        getNoticeArrayList((OnFinishedListener) this, textType);

    }

    @Override
    public void getNoticeArrayList(OnFinishedListener onFinishedListener, String textType) {
        NetworkApi
                .getInstance()
                .getAPI()
                .getMovies(textType, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseMoviesList>() {
                    @Override
                    public void onCompleted() {
                        Log.d("responEror", " completed");

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onFailure(e);

                    }

                    @Override
                    public void onNext(ResponseMoviesList responseMoviesList) {
                        if (responseMoviesList.getResults() != null) {
                            onFinishedListener.onFinished(responseMoviesList.getResults());
                            Log.d("responEror", " success ");

                        }
                    }
                });

    }


}
