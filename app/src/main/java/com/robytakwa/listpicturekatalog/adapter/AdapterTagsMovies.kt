package com.robytakwa.listpicturekatalog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robytakwa.listpicturekatalog.R
import com.robytakwa.listpicturekatalog.model.modeldetailmovies.GenresItem
import java.util.*

class AdapterTagsMovies(private val context: Context) : RecyclerView.Adapter<AdapterTagsMovies.ViewHolder>() {
    private var genresItems: ArrayList<GenresItem>? = null

    init {
        genresItems = ArrayList()
    }

    fun setGenresItems(genresItems: ArrayList<GenresItem>) {
        this.genresItems = genresItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_tags, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val genresItem = genresItems!![position]
        holder.txtTag.text = genresItem.name
    }

    override fun getItemCount(): Int {
        return genresItems!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtTag: TextView = itemView.findViewById(R.id.tv_tag)

    }
}
