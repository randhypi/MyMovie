package com.randhypi.mymovie.ui.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.data.TvShows
import com.randhypi.mymovie.data.source.MoviesRepository
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest{


    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<Movies>>


    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {

        val dummyMovies = DummyMovies.moviesDummy()
        val movies = MutableLiveData<List<Movies>>()
        movies.value = dummyMovies

        viewModel = MoviesViewModel(moviesRepository)

        `when`(moviesRepository.getMovies()).thenReturn(movies as MutableLiveData<List<Movies>>)
        val moviesEntities = viewModel.getMovies?.value as? LiveData<List<Movies>>

        verify<MoviesRepository>(moviesRepository, times(2)).getMovies()
        assertEquals(dummyMovies?.size,moviesEntities?.value?.size)

        viewModel.getMovies?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}