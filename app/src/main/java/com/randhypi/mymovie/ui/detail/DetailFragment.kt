package com.randhypi.mymovie.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.randhypi.mymovie.R
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.data.TvShows
import com.randhypi.mymovie.databinding.FragmentDetailBinding
import com.randhypi.mymovie.ui.home.movies.MoviesViewModel
import com.randhypi.mymovie.viewModel.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = DetailFragment::class.java.simpleName
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navController.currentDestination?.id == R.id.action_homeFragment_to_detailFragment) {
                        navController.popBackStack()
                    } else {
                        navController.popBackStack()
                    }

                    // view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
                }
            })
        binding.myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binding.myToolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(viewModelStore, factory)[DetailViewModel::class.java]


        val idArg = DetailFragmentArgs.fromBundle(arguments as Bundle).id
        val typeArg = DetailFragmentArgs.fromBundle(arguments as Bundle).type
        if (idArg != null && typeArg == "movies") {
            Log.d(TAG,"$idArg dan $typeArg")
            viewModel.setIdAndType(idArg)
            showDataMovies()
        } else if (idArg != null && typeArg == "tvshows") {
            Log.d(TAG,"$idArg dan $typeArg")
            viewModel.setIdAndType(idArg)
            showDataTvShows()
        }
    }

    private fun showDataMovies() {
        binding.myToolbar.title = "Movies Detail"
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        viewModel.getDetailMovies().observe(viewLifecycleOwner, { data ->
            if (data != null) {
                Log.d(TAG, data.toString())

                Glide.with(requireContext())
                    .load(data.poster!!)
                    .override(400, 700)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                    .into(binding.ivDetail)

                binding.tvTitle.text = data?.title
                binding.tvOriginalTitle.text = data?.original_title
                binding.tvOriginalLanguage.text = data?.original_language
                binding.tvReleaseDate.text = data?.release_date
                binding.tvOverview.text = data?.overview
                binding.tvPopularity.text = data?.popularity.toString()
            }
        })
    }

    private fun showDataTvShows() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        binding.myToolbar.title = "Tv Show Detail"

        viewModel.getDetailTvSHows().observe(viewLifecycleOwner, { data ->
            Log.d(TAG, data.toString())

            Glide.with(requireContext())
                .load(data.poster!!)
                .override(400, 700)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                .into(binding.ivDetail)

            binding.tvTitle.text = data?.name
            binding.tvOriginalTitle.text = data?.original_name
            binding.tvOriginalLanguage.text = data?.original_language
            binding.tvReleaseDate.text = data?.date
            binding.tvOverview.text = data?.overview
            binding.tvPopularity.text = data?.popularity.toString()
        })

    }


}