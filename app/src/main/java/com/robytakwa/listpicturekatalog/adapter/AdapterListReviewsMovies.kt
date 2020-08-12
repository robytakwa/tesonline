package com.robytakwa.listpicturekatalog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robytakwa.listpicturekatalog.R
import com.robytakwa.listpicturekatalog.model.modelreviewmovies.ResultsItemReviews
import java.util.*

class AdapterListReviewsMovies(private val context: Context) : RecyclerView.Adapter<AdapterListReviewsMovies.ViewHolder>() {
    private var reviewsArrayList: ArrayList<ResultsItemReviews>? = null

    init {
        reviewsArrayList = ArrayList()
    }

    fun setReviewsArrayList(reviewsArrayList: ArrayList<ResultsItemReviews>) {
        this.reviewsArrayList = reviewsArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_list_reviews, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultsItemReviews = reviewsArrayList!![position]
        holder.txtAuthor.text = resultsItemReviews.author
        holder.txtDescReviews.text = resultsItemReviews.content

    }

    override fun getItemCount(): Int {
        return reviewsArrayList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtAuthor: TextView = itemView.findViewById(R.id.tv_item_name_reviews)
        internal var txtDescReviews: TextView = itemView.findViewById(R.id.tv_desc_reviews)

    }
}
