package com.randhypi.mymovie.ui.home.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.randhypi.mymovie.R
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.databinding.ItemsMoviesBinding
import com.randhypi.mymovie.vo.Resource


class MoviesAdapter() : PagedListAdapter<MoviesEntity, MoviesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.moviesId == newItem.moviesId
            }
            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
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
        val movies = getItem(position)
        movies?.let {
            listViewHolder.bind(it)
        }
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsMoviesBinding.bind(itemView)
        fun bind(userItem: MoviesEntity) {

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
}