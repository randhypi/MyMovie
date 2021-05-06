package com.randhypi.mymovie.ui.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import com.randhypi.mymovie.vo.Resource
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
    private lateinit var observer: Observer<Resource<List<MoviesEntity>>>


    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(DummyMovies.moviesDummy())
        val movies = MutableLiveData<Resource<List<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies()?.value

        verify<MoviesRepository>(moviesRepository, times(1)).getMovies()
        assertEquals(dummyMovies?.data?.size,moviesEntities?.data?.size)

        viewModel.getMovies()?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}