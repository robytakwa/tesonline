package com.arbaelbarca.listmovieskatalog.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.listmovieskatalog.MainActivity;
import com.arbaelbarca.listmovieskatalog.R;
import com.arbaelbarca.listmovieskatalog.adapter.AdapterFavoriteMovies;
import com.arbaelbarca.listmovieskatalog.baseactivity.BaseActivity;
import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;
import com.arbaelbarca.listmovieskatalog.onclick.OnClickItem;
import com.arbaelbarca.listmovieskatalog.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieActivity extends BaseActivity {

    AdapterFavoriteMovies adapterFavoriteMovies;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvListFavoriteMovies)
    RecyclerView rvListFavoriteMovies;
    List<ResultsItem> resultsItems = new ArrayList<>();

    OnClickItem onClickItem = pos -> {
        ResultsItem itemsItem = resultsItems.get(pos);
        Intent intent = new Intent(getApplicationContext(), DetailMovieActivity.class);
        intent.putExtra(Constants.DATA_ITEMS, itemsItem);
        startActivity(intent);
    };
    @BindView(R.id.llNotFound)
    LinearLayout llNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {

        setToolbar(toolbar, "Favorite Movie");
        if (toolbar.getNavigationIcon() != null) {
            toolbar.setNavigationOnClickListener(view -> {
                finish();
            });
        }
        adapterFavoriteMovies = new AdapterFavoriteMovies(FavoriteMovieActivity.this);
        rvListFavoriteMovies.setLayoutManager(new LinearLayoutManager(this));
        rvListFavoriteMovies.setHasFixedSize(true);
        rvListFavoriteMovies.setAdapter(adapterFavoriteMovies);

        resultsItems = MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();

        if (resultsItems.size() > 0) {
            progressBar.setVisibility(View.GONE);
            adapterFavoriteMovies.setMovies(resultsItems);
            adapterFavoriteMovies.setOnClickItem(onClickItem);
            adapterFavoriteMovies.notifyDataSetChanged();
        } else {
            progressBar.setVisibility(View.GONE);
            llNotFound.setVisibility(View.VISIBLE);
        }
    }
}
