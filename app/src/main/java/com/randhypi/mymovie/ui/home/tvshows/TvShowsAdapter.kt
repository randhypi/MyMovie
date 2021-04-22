package com.randhypi.mymovie.ui.home.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.randhypi.mymovie.R
import com.randhypi.mymovie.data.movies.Movies
import com.randhypi.mymovie.data.tvshow.TvShows
import com.randhypi.mymovie.databinding.ItemsTvshowsBinding
import com.randhypi.mymovie.ui.home.movies.MoviesAdapter

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.ListViewHolder>() {

    private val mData = ArrayList<TvShows>()

    fun setData(items: ArrayList<TvShows>) {
        mData.clear()
        mData.addAll(items)
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
        listViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsTvshowsBinding.bind(itemView)
        fun bind(userItem: TvShows) {
            with(itemView) {
                binding.tvItemTitle.text = userItem.original_name
                binding.tvItemDate.text = userItem.date

                Glide.with(itemView.context)
                    .load(userItem.poster)
                    .fitCenter()
                    .into(binding.imgPoster)
            }
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItem) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShows)
    }
}