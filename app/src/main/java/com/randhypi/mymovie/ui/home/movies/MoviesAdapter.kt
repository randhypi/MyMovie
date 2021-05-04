package com.randhypi.mymovie.ui.home.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.randhypi.mymovie.R
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.databinding.ItemsMoviesBinding


class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {

    private val mData = ArrayList<MoviesEntity>()

    fun setData(items: ArrayList<MoviesEntity>) {
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
        private val binding = ItemsMoviesBinding.bind(itemView)
        fun bind(userItem: MoviesEntity) {
            with(itemView) {
                binding.tvItemTitle.text = userItem.originalTitle
                binding.tvItemDate.text = userItem.releaseDate

                Glide.with(itemView.context)
                    .load(userItem.poster)
                    .fitCenter()
                    .into(binding.imgPoster)
            }
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItem.id) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String?)
    }
}