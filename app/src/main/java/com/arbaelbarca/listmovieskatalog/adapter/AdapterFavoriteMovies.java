package com.arbaelbarca.listmovieskatalog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.listmovieskatalog.R;
import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;
import com.arbaelbarca.listmovieskatalog.onclick.OnClickItem;
import com.arbaelbarca.listmovieskatalog.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavoriteMovies extends RecyclerView.Adapter<AdapterFavoriteMovies.MyHolder> {
    private Context context;
    private List<ResultsItem> resultsItemArrayList;

    public void setMovies(List<ResultsItem> movies) {
        this.resultsItemArrayList = movies;
    }

    public AdapterFavoriteMovies(Context context) {
        this.context = context;
        resultsItemArrayList = new ArrayList<>();
    }


    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_listmovies, viewGroup, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        final ResultsItem hero = resultsItemArrayList.get(i);

        myHolder.bind(hero);
        myHolder.itemView.setOnClickListener(v -> {
            if (onClickItem != null)
                onClickItem.clickItemDetail(i);
        });
    }

    @Override
    public int getItemCount() {
        return resultsItemArrayList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDeskripsi, txtDate;
        ImageView imgHero;

        MyHolder(View view) {
            super(view);
            imgHero = view.findViewById(R.id.img_item_photo);
            txtName = view.findViewById(R.id.tv_item_name);
            txtDeskripsi = view.findViewById(R.id.tv_item_from);
            txtDate = view.findViewById(R.id.tv_date);
        }

        @SuppressLint("CheckResult")
        void bind(ResultsItem hero) {
            txtName.setText(hero.getTitle());
            txtDeskripsi.setText(hero.getOverview());
            txtDate.setText(hero.getReleaseDate());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.noimage);
            requestOptions.error(R.drawable.noimage);

            Glide.with(context)
                    .load(Constants.IMG_POSTER + hero.getPosterPath())
                    .apply(requestOptions)
                    .into(imgHero);

        }
    }

}

