package com.randhypi.mymovie.ui.home.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.randhypi.mymovie.R
import com.capstone.core.domain.model.Movies
import com.capstone.core.ui.MoviesAdapter
import com.randhypi.mymovie.databinding.ItemsMoviesfavBinding


class MovieFavAdapter : RecyclerView.Adapter<MovieFavAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movies>()

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.items_movies, viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, position: Int) {
        val data = listData[position]
        listViewHolder.bind(data)
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsMoviesfavBinding.bind(itemView)
        fun bind(userItem: Movies) {
                binding.tvItemTitle.text = userItem.originalTitle
                binding.tvItemDate.text = userItem.releaseDate

                Glide.with(itemView.context)
                    .load(userItem.poster)
                    .fitCenter()
                    .into(binding.imgPoster)

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItem.moviesId) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String?)
    }

    override fun getItemCount(): Int = listData.size
}