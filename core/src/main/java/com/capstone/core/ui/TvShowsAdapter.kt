package com.capstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.core.R
import com.capstone.core.databinding.ItemsTvshowsBinding
import com.capstone.core.domain.model.TvShows

class TvShowsAdapter : PagedListAdapter<TvShows, TvShowsAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShows>() {
            override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem == newItem
            }
        }
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
        val tv = getItem(position)
        tv?.let {
            listViewHolder.bind(it)
        }
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsTvshowsBinding.bind(itemView)
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
}