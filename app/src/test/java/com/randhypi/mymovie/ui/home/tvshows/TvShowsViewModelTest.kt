package com.randhypi.mymovie.ui.home.tvshows

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp(){
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getGetTvShows() {
        val value = viewModel.getTvShows
        assertNotNull(value)
        assertEquals(10, value.size)
    }
}