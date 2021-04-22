package com.randhypi.mymovie.ui.home.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.randhypi.mymovie.data.movies.Movies
import com.randhypi.mymovie.databinding.FragmentMoviesBinding

import com.randhypi.mymovie.ui.home.HomeFragmentDirections

class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showGridAdapter()
    }

    private fun selectedMovies(movies: Movies){
        Toast.makeText(context,"Kamu memilih ${movies.original_title}",Toast.LENGTH_SHORT).show()
        val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        toDetail.id = movies.id
        toDetail.type = "movies"

        view?.findNavController()?.navigate(toDetail)
    }

    private fun showGridAdapter() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.notifyDataSetChanged()
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)
        binding.rvMoviesList.adapter = moviesAdapter
        moviesAdapter.setData(moviesViewModel.getMovies)

        moviesAdapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movies) {
                selectedMovies(data)
            }
        })
    }
}