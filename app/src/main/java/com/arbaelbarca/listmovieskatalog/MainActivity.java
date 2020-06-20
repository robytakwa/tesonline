package com.arbaelbarca.listmovieskatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.arbaelbarca.listmovieskatalog.adapter.AdapterListMovies;
import com.arbaelbarca.listmovieskatalog.database.room.FavoriteDatabase;
import com.arbaelbarca.listmovieskatalog.baseactivity.BaseActivity;
import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;
import com.arbaelbarca.listmovieskatalog.onclick.OnClickItem;
import com.arbaelbarca.listmovieskatalog.presenter.listmoviespresenter.ListMoviesPresenterImpl;
import com.arbaelbarca.listmovieskatalog.presenter.listmoviespresenter.ListMoviePresenter;
import com.arbaelbarca.listmovieskatalog.ui.activity.DetailMovieActivity;
import com.arbaelbarca.listmovieskatalog.ui.activity.FavoriteMovieActivity;
import com.arbaelbarca.listmovieskatalog.utils.Constants;
import com.arbaelbarca.listmovieskatalog.view.MainContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.MainView, PopupMenu.OnMenuItemClickListener {

    ListMoviePresenter presenter;
    AdapterListMovies adapterListMovies;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvListUser)
    RecyclerView rvListUser;

    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.llNotFound)
    LinearLayout llNotFound;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnCategory)
    Button btnCategory;


    ArrayList<ResultsItem> resultsItemsList = new ArrayList<>();
    PopupMenu popupMenu;
    public static FavoriteDatabase favoriteDatabase;

    OnClickItem onClickItem = pos -> {
        ResultsItem itemsItem = resultsItemsList.get(pos);
        Intent intent = new Intent(getApplicationContext(), DetailMovieActivity.class);
        intent.putExtra(Constants.DATA_ITEMS, itemsItem);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initial();

        btnCategory.setOnClickListener(view -> {
            popupMenu.show();

        });


    }


    private void getListType(String type) {
        showProgress();
        resultsItemsList.clear();
        presenter.refreshData(type);
        getSupportActionBar().setTitle(type);
    }

    private void initial() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Popular");

        favoriteDatabase = Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class, "myfavdbnew").allowMainThreadQueries().build();

        presenter = new ListMoviePresenter(this  , new ListMoviesPresenterImpl(MainActivity.this));
        presenter.requestFromDataServer();

        popupMenu = new PopupMenu(MainActivity.this, btnCategory);
        popupMenu.getMenuInflater().inflate(R.menu.item_more_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);

        adapterListMovies = new AdapterListMovies(this);

        linearLayoutManager = new LinearLayoutManager(this);
        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setHasFixedSize(true);
        adapterListMovies.setOnClickItem(onClickItem);


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<ResultsItem> resultsItemArrayList) {
        resultsItemsList = resultsItemArrayList;
        adapterListMovies.setMovies(resultsItemsList);
        rvListUser.setAdapter(adapterListMovies);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        presenter.onFailure(throwable);
        progressBar.setVisibility(View.GONE);
        showToast(getString(R.string.text_server_eror));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.ic_upcoming) {
            getListType(getString(R.string.text_upcoming_new));
        } else if (menuItem.getItemId() == R.id.ic_nowplaying) {
            getListType(getString(R.string.text_nowplaying_api));
        } else if (menuItem.getItemId() == R.id.ic_popular) {
            getListType(getString(R.string.text_popular_api));
        } else if (menuItem.getItemId() == R.id.ic_toprated) {
            getListType(getString(R.string.text_toprated_api));

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_love_fav) {
            startActivity(new Intent(getApplicationContext(), FavoriteMovieActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
