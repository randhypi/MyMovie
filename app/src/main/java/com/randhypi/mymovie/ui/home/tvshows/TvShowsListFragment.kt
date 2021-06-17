package com.randhypi.mymovie.ui.home.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.core.data.Resource
import com.randhypi.mymovie.databinding.FragmentTvShowsBinding
import com.randhypi.mymovie.ui.home.HomeFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel
import com.capstone.core.data.Status
import com.capstone.core.ui.TvShowsAdapter

class TvShowsListFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvshowsAdapter: TvShowsAdapter
    private val viewModel: TvShowsViewModel by viewModel()


    companion object{
        val TAG = TvShowsListFragment::class.java.simpleName
    }

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

    private fun selectedMovies(tvShows: String){
        Toast.makeText(context,"Kamu memilih ${tvShows}", Toast.LENGTH_SHORT).show()
        val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        toDetail.id = tvShows
        toDetail.type = "tvshows"

        view?.findNavController()?.navigate(toDetail)
    }

    private fun showGridAdapter() {

        tvshowsAdapter = TvShowsAdapter()
        tvshowsAdapter.notifyDataSetChanged()
        binding.rvTvShowsList.layoutManager = GridLayoutManager(context, 2)
        binding.rvTvShowsList.adapter = tvshowsAdapter

        viewModel.getTvShows()?.observe(viewLifecycleOwner,{ tv ->

            when(tv){
               is Resource.Loading  -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    tvshowsAdapter.setData(tv.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

        })

        tvshowsAdapter.setOnItemClickCallback(object :  TvShowsAdapter.OnItemClickCallback{

            override fun onItemClicked(data: String) {
                selectedMovies(data)
            }
        })
    }
}