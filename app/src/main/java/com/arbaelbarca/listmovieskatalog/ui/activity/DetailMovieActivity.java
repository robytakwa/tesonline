package com.arbaelbarca.listmovieskatalog.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.arbaelbarca.listmovieskatalog.MainActivity;
import com.arbaelbarca.listmovieskatalog.R;
import com.arbaelbarca.listmovieskatalog.adapter.AdapterListReviewsMovies;
import com.arbaelbarca.listmovieskatalog.adapter.AdapterTagsMovies;
import com.arbaelbarca.listmovieskatalog.baseactivity.BaseActivity;
import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.ResponseDetailMovies;
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResultsItemReviews;
import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;
import com.arbaelbarca.listmovieskatalog.presenter.detailmoviespresenter.DetailMoviesPresenter;
import com.arbaelbarca.listmovieskatalog.presenter.detailmoviespresenter.DetailMoviesPresenterImpl;
import com.arbaelbarca.listmovieskatalog.utils.Constants;
import com.arbaelbarca.listmovieskatalog.view.DetailMoviesView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends BaseActivity implements DetailMoviesView.MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    ResultsItem itemsItem;

    @BindView(R.id.img_item_photo)
    ImageView imgItemPhoto;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_item_from)
    TextView tvItemFrom;
    @BindView(R.id.imgLoveFav)
    ImageView imgLoveFav;
    @BindView(R.id.rlDetail)
    RelativeLayout rlDetail;
    @BindView(R.id.ratingMovies)
    RatingBar ratingMovies;
    @BindView(R.id.txtRatingBar)
    TextView txtRatingBar;
    @BindView(R.id.rvReviewMovies)
    RecyclerView rvReviewMovies;
    DetailMoviesPresenter detailMoviesPresenter;
    AdapterListReviewsMovies adapterListReviewsMovies;
    @BindView(R.id.progressBarRevies)
    ProgressBar progressBarRevies;
    @BindView(R.id.imgBackdrop)
    ImageView imgBackdrop;

    AdapterTagsMovies adapterTagsMovies;
    @BindView(R.id.rvTags)
    RecyclerView rvTags;
    @BindView(R.id.btnFloatFav)
    FloatingActionButton btnFloatFav;
    @BindView(R.id.txtNotFound)
    TextView txtNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);
        ButterKnife.bind(this);

        initial();
    }

    @SuppressLint("CheckResult")
    private void initial() {

        setToolbar(toolbar, "Detail Movie");
        if (toolbar.getNavigationIcon() != null) {
            toolbar.setNavigationOnClickListener(view -> {
                finish();
            });
        }
        itemsItem = getIntent().getParcelableExtra(Constants.DATA_ITEMS);
        detailMoviesPresenter = new DetailMoviesPresenter(this, new DetailMoviesPresenterImpl(DetailMovieActivity.this));
        initAdapter();

        if (itemsItem != null) {
            tvItemName.setText(itemsItem.getTitle());

            tvItemFrom.setText(itemsItem.getOverview());
            tvDate.setText(itemsItem.getReleaseDate());
            ratingMovies.setRating((float) itemsItem.getVoteAverage());
            txtRatingBar.setText(String.valueOf(itemsItem.getVoteAverage()));

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.noimage);
            requestOptions.error(R.drawable.noimage);

            Glide.with(this)
                    .load(Constants.IMG_POSTER + itemsItem.getPosterPath())
                    .apply(requestOptions)
                    .into(imgItemPhoto);

            RequestOptions requestOptionsBackdrop = new RequestOptions();
            requestOptionsBackdrop.placeholder(R.drawable.noimage);
            requestOptionsBackdrop.error(R.drawable.noimage);

            Glide.with(this)
                    .load(Constants.IMG_POSTER + itemsItem.getBackdropPath())
                    .apply(requestOptionsBackdrop)
                    .into(imgBackdrop);

            detailMoviesPresenter.requestFromDataServer(String.valueOf(itemsItem.getId()));

        }

        btnFloatFav.setOnClickListener(view -> {
            getFavMovies(itemsItem);
        });

        statusFav();


    }

    private void initAdapter() {
        adapterTagsMovies = new AdapterTagsMovies(this);
        adapterListReviewsMovies = new AdapterListReviewsMovies(this);

        rvReviewMovies.setAdapter(adapterListReviewsMovies);
        rvReviewMovies.setLayoutManager(new LinearLayoutManager(this));
        rvReviewMovies.setHasFixedSize(true);

        StaggeredGridLayoutManager mLayoutManagerTag = new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL);
        rvTags.setAdapter(adapterTagsMovies);
        rvTags.setLayoutManager(mLayoutManagerTag);
        rvTags.setHasFixedSize(true);


    }


    private void getFavMovies(ResultsItem resultsItem) {
        ResultsItem favoriteList = new ResultsItem();
        favoriteList.setId(resultsItem.getId());
        favoriteList.setTitle(resultsItem.getTitle());
        favoriteList.setPosterPath(resultsItem.getPosterPath());
        favoriteList.setReleaseDate(resultsItem.getReleaseDate());
        favoriteList.setOverview(resultsItem.getOverview());
        favoriteList.setBackdropPath(resultsItem.getBackdropPath());
        favoriteList.setVoteAverage(resultsItem.getVoteAverage());

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(favoriteList.getId()) != 1) {
            imgLoveFav.setImageResource(R.drawable.ic_love_red);
            btnFloatFav.setImageResource(R.drawable.ic_love_red);
            MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
            Snackbar.make(rlDetail, "Tersimpan ke favorite", Snackbar.LENGTH_LONG).show();
        } else {
            imgLoveFav.setImageResource(R.drawable.ic_love_white);
            btnFloatFav.setImageResource(R.drawable.ic_love_white);
            MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
            Snackbar.make(rlDetail, "Batalkan Favorite", Snackbar.LENGTH_LONG).show();
        }
    }

    void statusFav() {
        if (itemsItem != null)
            if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(itemsItem.getId()) == 1) {
                imgLoveFav.setImageResource(R.drawable.ic_love_red);
                btnFloatFav.setImageResource(R.drawable.ic_love_red);
            } else {
                imgLoveFav.setImageResource(R.drawable.ic_love_white);
                btnFloatFav.setImageResource(R.drawable.ic_love_white);
            }
    }

    @Override
    public void showProgress() {
        progressBarRevies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarRevies.setVisibility(View.GONE);
    }

    @Override
    public void setDataTags(ResponseDetailMovies responseDetailMovies) {
        if (responseDetailMovies != null) {
            adapterTagsMovies.setGenresItems(responseDetailMovies.getGenres());
            adapterTagsMovies.notifyDataSetChanged();

        }

    }

    @Override
    public void setDataReview(ArrayList<ResultsItemReviews> resultsItemArrayList) {
        if (resultsItemArrayList.size() > 0) {
            adapterListReviewsMovies.setReviewsArrayList(resultsItemArrayList);
            adapterListReviewsMovies.notifyDataSetChanged();
            txtNotFound.setVisibility(View.GONE);
        } else {
            hideProgress();
            txtNotFound.setVisibility(View.VISIBLE);
            txtNotFound.setText("Not found data reviews");
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        detailMoviesPresenter.onFailure(throwable);
        hideProgress();
        showToast(getString(R.string.text_server_eror));

    }
}
