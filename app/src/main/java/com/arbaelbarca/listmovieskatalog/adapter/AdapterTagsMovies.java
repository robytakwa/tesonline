package com.arbaelbarca.listmovieskatalog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.listmovieskatalog.R;
import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.GenresItem;

import java.util.ArrayList;

public class AdapterTagsMovies extends RecyclerView.Adapter<AdapterTagsMovies.ViewHolder> {
    private Context context;
    private ArrayList<GenresItem> genresItems;

    public AdapterTagsMovies(Context context) {
        this.context = context;
        genresItems = new ArrayList<>();
    }

    public void setGenresItems(ArrayList<GenresItem> genresItems) {
        this.genresItems = genresItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_tags, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GenresItem genresItem = genresItems.get(position);
        holder.txtTag.setText(genresItem.getName());
    }

    @Override
    public int getItemCount() {
        return genresItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTag = itemView.findViewById(R.id.tv_tag);
        }
    }
}
