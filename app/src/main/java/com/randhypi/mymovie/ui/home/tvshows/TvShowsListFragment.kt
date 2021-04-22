package com.randhypi.mymovie.ui.home.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.randhypi.mymovie.data.movies.Movies
import com.randhypi.mymovie.data.tvshow.TvShows
import com.randhypi.mymovie.databinding.FragmentMoviesBinding
import com.randhypi.mymovie.databinding.FragmentTvShowsBinding
import com.randhypi.mymovie.ui.home.HomeFragmentDirections
import com.randhypi.mymovie.ui.home.movies.MoviesAdapter

class TvShowsListFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private val tvShowsViewModel: TvShowsViewModel by viewModels()
    private lateinit var tvshowsAdapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showGridAdapter()
    }

    private fun selectedMovies(tvShows: TvShows){
        Toast.makeText(context,"Kamu memilih ${tvShows.original_name}", Toast.LENGTH_SHORT).show()
        val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        toDetail.id = tvShows.id
        toDetail.type = "tvshows"

        view?.findNavController()?.navigate(toDetail)
    }

    private fun showGridAdapter() {
        tvshowsAdapter = TvShowsAdapter()
        tvshowsAdapter.notifyDataSetChanged()
        binding.rvTvShowsList.layoutManager = GridLayoutManager(context, 2)
        binding.rvTvShowsList.adapter = tvshowsAdapter
        tvshowsAdapter.setData(tvShowsViewModel.getTvShows)

        tvshowsAdapter.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvShows) {
                selectedMovies(data)
            }
        })
    }
}