package com.randhypi.mymovie.ui.home.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.randhypi.mymovie.databinding.FragmentMoviesBinding
import com.randhypi.mymovie.ui.favorite.movies.MoviesFavListFragment

import com.randhypi.mymovie.ui.home.HomeFragmentDirections
import com.randhypi.mymovie.viewModel.ViewModelFactory
import com.randhypi.mymovie.vo.Status

class  MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter

    companion object{
        val TAG = MoviesFavListFragment::class.java.simpleName
    }

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

    private fun selectedMovies(movies: String){
        Toast.makeText(context,movies,Toast.LENGTH_SHORT).show()
        val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        toDetail.id = movies
        toDetail.type = "movies"

        view?.findNavController()?.navigate(toDetail)
    }

    private fun showGridAdapter() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

        moviesAdapter = MoviesAdapter()
        moviesAdapter.notifyDataSetChanged()
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)
        binding.rvMoviesList.adapter = moviesAdapter

        viewModel.getMovies().observe(viewLifecycleOwner,{ movies ->
           when(movies.status){
               Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
               Status.SUCCESS -> {
                   binding?.progressBar?.visibility = View.GONE
                   moviesAdapter.submitList(movies.data)
               }
               Status.ERROR -> {
                   binding?.progressBar?.visibility = View.GONE
                   Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
               }
           }

        })

        moviesAdapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: String?) {
                if (data != null) {
                    selectedMovies(data)
                }
            }
        })
    }
}