package com.randhypi.mymovie.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.randhypi.mymovie.R
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.databinding.FragmentDetailBinding
import com.randhypi.mymovie.viewModel.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    private var statFav: Boolean = false

    companion object {
        val TAG = DetailFragment::class.java.simpleName
        val MOVIESARG = "movies"
        val TVSHOWSARG = "tvshows"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(viewModelStore, factory)[DetailViewModel::class.java]

        val idArg = DetailFragmentArgs.fromBundle(arguments as Bundle).id
        val typeArg = DetailFragmentArgs.fromBundle(arguments as Bundle).type

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navController.currentDestination?.id == R.id.action_homeFragment_to_detailFragment) {
                        navController.popBackStack()
                    } else {
                        navController.popBackStack()
                    }
                }
            })

        binding.myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binding.myToolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        binding.myToolbar.inflateMenu(R.menu.menu)

        if (idArg != null && typeArg == MOVIESARG) {
            Log.d(TAG, "$idArg dan $typeArg")
            viewModel.setIdAndType(idArg)
            showDataMovies()
            updateFav(typeArg)
        } else if (idArg != null && typeArg == TVSHOWSARG) {
            Log.d(TAG, "$idArg dan $typeArg")
            viewModel.setIdAndType(idArg)
            showDataTvShows()
            updateFav(typeArg)
        }
    }

    private fun showDataMovies() {
        binding.myToolbar.title = "Movies Detail"
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val favItem = binding.myToolbar.menu.findItem(R.id.action_favorite)

        viewModel.getDetailMovies().observe(viewLifecycleOwner, { data ->
            if (data != null) {
                Log.d(TAG, "$statFav on")
                statFav = data.favorite!!
                Log.d(TAG, "$statFav yap")

                btnFavMovie(data)

                if (statFav){
                    favItem.setIcon(R.drawable.ic_favorite_24)
                }else {
                    favItem.setIcon(R.drawable.ic_unfavorite24)
                }

                Glide.with(requireContext())
                    .load(data.poster!!)
                    .override(400, 700)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                    .into(binding.ivDetail)

                binding.tvTitle.text = data?.title
                binding.tvOriginalTitle.text = data?.originalTitle
                binding.tvOriginalLanguage.text = data?.originalLanguage
                binding.tvReleaseDate.text = data?.releaseDate
                binding.tvOverview.text = data?.overview
                binding.tvPopularity.text = data?.popularity.toString()
            }
        })
    }

    private fun showDataTvShows() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val favItem = binding.myToolbar.menu.findItem(R.id.action_favorite)
        binding.myToolbar.title = "Tv Show Detail"

        viewModel.getDetailTvSHows().observe(viewLifecycleOwner, { data ->
            Log.d(TAG, data.toString())
            statFav = data.favorite ?: false
            btnFavTv(data)
            Glide.with(requireContext())
                .load(data.poster!!)
                .override(400, 700)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                .into(binding.ivDetail)

            if (statFav){
                favItem.setIcon(R.drawable.ic_favorite_24)
            }else {
                favItem.setIcon(R.drawable.ic_unfavorite24)
            }

            binding.tvTitle.text = data?.name
            binding.tvOriginalTitle.text = data?.originalName
            binding.tvOriginalLanguage.text = data?.originalLanguage
            binding.tvReleaseDate.text = data?.date
            binding.tvOverview.text = data?.overview
            binding.tvPopularity.text = data?.popularity.toString()
        })
    }


    fun btnFavMovie(data: MoviesEntity) {
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_favorite -> {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                    val factory = ViewModelFactory.getInstance(requireActivity())
                    val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
                    viewModel.setFavMovie(data)
                    true
                }
                else -> false
            }
        }
    }


    fun btnFavTv(data: TvShowsEntity) {

        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_favorite -> {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                    val factory = ViewModelFactory.getInstance(requireActivity())
                    val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
                    viewModel.setFavTvShow(data)
                    true
                }
                else -> false
            }
        }
    }

    fun updateFav(type: String) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val favItem = binding.myToolbar.menu.findItem(R.id.action_favorite)
        if (type.equals(MOVIESARG)) {
            viewModel.getMovieFav().observe(viewLifecycleOwner, {
                statFav = it
                Log.d(TAG, statFav.toString())
                if (!statFav) {
                    favItem.setIcon(R.drawable.ic_unfavorite24)
                } else if (statFav) {
                    favItem.setIcon(R.drawable.ic_favorite_24)
                }
            })
        } else if (type.equals(TVSHOWSARG)) {
            viewModel.getTvFav().observe(viewLifecycleOwner, {
                statFav = it
                Log.d(TAG, statFav.toString())
                if (!statFav) {
                    favItem.setIcon(R.drawable.ic_unfavorite24)
                } else if (statFav) {
                    favItem.setIcon(R.drawable.ic_favorite_24)
                }
            })
        }
    }
}