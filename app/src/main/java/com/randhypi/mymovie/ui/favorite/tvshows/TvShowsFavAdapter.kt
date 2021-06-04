package com.randhypi.mymovie.ui.home.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.core.domain.model.Movies
import com.randhypi.mymovie.R
import com.capstone.core.domain.model.TvShows
import com.capstone.core.ui.MoviesAdapter
import com.capstone.core.ui.TvShowsAdapter
import com.randhypi.mymovie.databinding.ItemsTvshowsfavBinding

class TvShowsFavAdapter() : RecyclerView.Adapter<TvShowsFavAdapter.ListViewHolder>() {

    private var listData = ArrayList<TvShows>()

    fun setData(newListData: List<TvShows>?) {
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
        private val binding = ItemsTvshowsfavBinding.bind(itemView)
        fun bind(userItem: TvShows) {

                binding.tvItemTitle.text = userItem.originalName
                binding.tvItemDate.text = userItem.date

                Glide.with(itemView.context)
                    .load(userItem.poster)
                    .fitCenter()
                    .into(binding.imgPoster)

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItem?.id!!) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }

    override fun getItemCount(): Int = listData.size
}