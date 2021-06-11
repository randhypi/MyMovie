package com.randhypi.mymovie.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.randhypi.mymovie.R
import com.randhypi.mymovie.favorite.databinding.FragmentFavoriteBinding
import com.randhypi.mymovie.favorite.di.viewModelModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = FavoriteFragment::class.java.simpleName

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(viewModelModule)

        val sectionsPagerFavAdapter = SectionsPagerFavAdapter(context as FragmentActivity)
        val viewPager: ViewPager2 = binding.viewPagerfav
        viewPager.adapter = sectionsPagerFavAdapter
        val tabs: TabLayout = binding.tabsfav
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        activity?.actionBar?.elevation = 0f

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val navController = findNavController()
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navController.currentDestination?.id == R.id.action_homeFragment_to_favoriteFragment) {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}