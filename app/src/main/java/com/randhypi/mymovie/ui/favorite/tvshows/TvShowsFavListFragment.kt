package com.randhypi.mymovie.ui.favorite.tvshows

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.databinding.FragmentTvShowsfavBinding
import com.randhypi.mymovie.ui.favorite.FavoriteFragmentDirections
import com.randhypi.mymovie.ui.home.HomeFragmentDirections
import com.randhypi.mymovie.ui.home.tvshows.TvShowsFavAdapter
import com.randhypi.mymovie.ui.home.tvshows.TvShowsFavViewModel
import com.randhypi.mymovie.viewModel.ViewModelFactory

class TvShowsFavListFragment : Fragment() {

    private var _binding: FragmentTvShowsfavBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowsFavAdapter: TvShowsFavAdapter

    companion object{
        val TAG = TvShowsFavListFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsfavBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showGridAdapter()
    }

    private fun selectedMovies(tvShows: String){
        Toast.makeText(context,"Kamu memilih ${tvShows}", Toast.LENGTH_SHORT).show()
        val toDetail = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment()
        toDetail.id = tvShows
        toDetail.type = "tvshows"

        view?.findNavController()?.navigate(toDetail)
    }

    private fun showGridAdapter() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowsFavViewModel::class.java]

        tvShowsFavAdapter = TvShowsFavAdapter()
        tvShowsFavAdapter.notifyDataSetChanged()
        binding.rvTvShowsFavList.layoutManager = GridLayoutManager(context, 2)
        binding.rvTvShowsFavList.adapter = tvShowsFavAdapter

        viewModel.getTvShowsFav()?.observe(viewLifecycleOwner,{ tv ->
            tvShowsFavAdapter.submitList(tv)
        })

        tvShowsFavAdapter.setOnItemClickCallback(object :  TvShowsFavAdapter.OnItemClickCallback{

            override fun onItemClicked(data: String) {
                if (data != null) {
                    selectedMovies(data)
                }
            }
        })
    }
}