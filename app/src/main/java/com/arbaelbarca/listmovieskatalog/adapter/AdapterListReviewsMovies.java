package com.arbaelbarca.listmovieskatalog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.listmovieskatalog.R;
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResultsItemReviews;

import java.util.ArrayList;

public class AdapterListReviewsMovies extends RecyclerView.Adapter<AdapterListReviewsMovies.ViewHolder> {
    private Context context;
    private ArrayList<ResultsItemReviews> reviewsArrayList;

    public AdapterListReviewsMovies(Context context) {
        this.context = context;
        reviewsArrayList = new ArrayList<>();
    }

    public void setReviewsArrayList(ArrayList<ResultsItemReviews> reviewsArrayList) {
        this.reviewsArrayList = reviewsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_list_reviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultsItemReviews resultsItemReviews = reviewsArrayList.get(position);
        holder.txtAuthor.setText(resultsItemReviews.getAuthor());
        holder.txtDescReviews.setText(resultsItemReviews.getContent());

    }

    @Override
    public int getItemCount() {
        return reviewsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAuthor, txtDescReviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAuthor = itemView.findViewById(R.id.tv_item_name_reviews);
            txtDescReviews = itemView.findViewById(R.id.tv_desc_reviews);

        }
    }
}
