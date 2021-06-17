package com.randhypi.mymovie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.randhypi.mymovie.R
import com.randhypi.mymovie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    companion object {
        val TAG = HomeFragment::class.java.simpleName

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val sectionsPagerAdapter = SectionsPagerAdapter(context as FragmentActivity)
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.isSaveEnabled = false

        val tabs: TabLayout = view.findViewById(R.id.tabs)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        activity?.actionBar?.elevation = 0f
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                 System.exit(0)
                }
            })

        binding?.myToolbar?.inflateMenu(R.menu.menu)

        binding?.myToolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_favorite -> {
                    val toFav = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()

                    view.findNavController().navigate(toFav)
                    true
                }
                else -> false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val viewPager2 = binding?.viewPager

        viewPager2?.let {
            it.adapter = null
        }

        _binding = null

    }

}