package com.randhypi.mymovie.ui.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.utils.DummyMovies
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
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>


    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies()?.value

        verify<MoviesRepository>(moviesRepository, times(1)).getMovies()
        assertEquals(5,moviesEntities?.data?.size)

        viewModel.getMovies()?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}