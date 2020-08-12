package com.robytakwa.listpicturekatalog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robytakwa.listpicturekatalog.R
import com.robytakwa.listpicturekatalog.model.movies.ResultsItem
import com.robytakwa.listpicturekatalog.onclick.OnClickItem
import com.robytakwa.listpicturekatalog.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class AdapterListMovies(private val context: Context) : RecyclerView.Adapter<AdapterListMovies.MyHolder>() {
    private var resultsItemArrayList: ArrayList<ResultsItem>? = null


    private var onClickItem: OnClickItem? = null

    fun setMovies(movies: ArrayList<ResultsItem>) {
        this.resultsItemArrayList = movies
    }

    init {
        resultsItemArrayList = ArrayList()
    }

    fun setOnClickItem(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_listmovies, viewGroup, false)
        return MyHolder(view)

    }

    override fun onBindViewHolder(myHolder: MyHolder, i: Int) {
        val hero = resultsItemArrayList!![i]

        myHolder.bind(hero)
        myHolder.itemView.setOnClickListener { v ->
            if (onClickItem != null)
                onClickItem!!.clickItemDetail(i)
        }
    }

    override fun getItemCount(): Int {
        return resultsItemArrayList!!.size
    }


    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView = view.findViewById(R.id.tv_item_name)
        private var txtDeskripsi: TextView = view.findViewById(R.id.tv_item_from)
        private var txtDate: TextView = view.findViewById(R.id.tv_date)
        var imgHero: ImageView = view.findViewById(R.id.img_item_photo)

        @SuppressLint("CheckResult")
        fun bind(hero: ResultsItem) {
            txtName.text = hero.title
            txtDeskripsi.text = hero.overview
            txtDate.text = hero.releaseDate

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.noimage)
            requestOptions.error(R.drawable.noimage)

            Glide.with(context)
                    .load(Constants.IMG_POSTER + hero.posterPath)
                    .apply(requestOptions)
                    .into(imgHero)

        }
    }

}

