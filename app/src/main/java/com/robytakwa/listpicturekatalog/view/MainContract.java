package com.robytakwa.listpicturekatalog.view;

import com.robytakwa.listpicturekatalog.model.movies.ResultsItem;

import java.util.ArrayList;

public interface MainContract {

    interface presenter {
        void onDestroy();

        void requestFromDataServer();

        void refreshData(String type);
    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<ResultsItem> resultsItemArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<ResultsItem> resultsItems);

            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener, String textType);

    }
}
