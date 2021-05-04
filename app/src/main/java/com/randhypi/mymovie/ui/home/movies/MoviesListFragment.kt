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
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.databinding.FragmentMoviesBinding

import com.randhypi.mymovie.ui.home.HomeFragmentDirections
import com.randhypi.mymovie.viewModel.ViewModelFactory

class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter

    companion object{
        val TAG = MoviesListFragment::class.java.simpleName
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

        viewModel.getMovies().observe(viewLifecycleOwner,{movies ->
           // Log.d(TAG,"${movies[0].original_title} home list")
            moviesAdapter.setData(movies as ArrayList<MoviesEntity>)
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