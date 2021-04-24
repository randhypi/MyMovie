package com.randhypi.mymovie.ui.detail

import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DummyMovies.moviesDummy()
    private val dummyTvShows = DummyTvShows.tvShowsDummy()

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
    }


    @Test
    fun getDetailMovies() {
        viewModel.setIdAndType("19404")
        val movies = viewModel.getDetailMovies()
        val dummy = dummyMovies.find {
            it.id == movies?.id
        }

        print(movies?.id)
        assertNotNull(movies)
        assertEquals(dummy?.poster,movies?.poster)
        assertEquals(dummy?.id,movies?.id)
        assertEquals(dummy?.title,movies?.title)
    }

    @Test
    fun getDetailTvSHows() {
        viewModel.setIdAndType("95057")
        val tvShows = viewModel.getDetailTvSHows()
        val dummy = dummyTvShows.find {
            it.id == tvShows?.id
        }

        print(tvShows?.original_name)
        assertNotNull(tvShows)
        assertEquals(dummy?.poster,tvShows?.poster)
        assertEquals(dummy?.id,tvShows?.id)
        assertEquals(dummy?.name,tvShows?.name)
    }

}