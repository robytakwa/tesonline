package com.robytakwa.listpicturekatalog.presenter.listmoviespresenter

import android.content.Context
import android.util.Log
import com.robytakwa.listpicturekatalog.model.movies.ResponseMoviesList
import com.robytakwa.listpicturekatalog.network.NetworkApi
import com.robytakwa.listpicturekatalog.utils.Constants.LANGUAGE
import com.robytakwa.listpicturekatalog.view.MainContract
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListMoviesPresenterImpl(private val context: Context) : MainContract.GetNoticeIntractor, MainContract.presenter {


    override fun onDestroy() {

    }

    override fun requestFromDataServer() {

    }

    override fun refreshData(textType: String) {
        getNoticeArrayList(this as MainContract.GetNoticeIntractor.OnFinishedListener, textType)

    }

    override fun getNoticeArrayList(onFinishedListener: MainContract.GetNoticeIntractor.OnFinishedListener, textType: String) {
        NetworkApi
                .instance
                .api
                .getMovies(textType, LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseMoviesList>() {
                    override fun onCompleted() {
                        Log.d("responEror", " completed")

                    }

                    override fun onError(e: Throwable) {
                        onFinishedListener.onFailure(e)

                    }

                    override fun onNext(responseMoviesList: ResponseMoviesList) {
                        if (responseMoviesList.results != null) {
                            onFinishedListener.onFinished(responseMoviesList.results)
                            Log.d("responEror", " success ")

                        }
                    }
                })

    }


}
