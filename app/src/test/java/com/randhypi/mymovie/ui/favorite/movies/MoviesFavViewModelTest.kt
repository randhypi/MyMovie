package com.randhypi.mymovie.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.ui.home.movies.MoviesFavViewModel
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesFavViewModelTest{

    private lateinit var viewModel: MoviesFavViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>


    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesFavViewModel(moviesRepository)
    }

    @Test
    fun getMoviesFav() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovies


        `when`(moviesRepository.getFavMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMoviesFav()?.value

        verify<MoviesRepository>(moviesRepository, Mockito.times(1)).getFavMovies()
        Assert.assertEquals(5, moviesEntities?.size)


        viewModel.getMoviesFav()?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)


    }

}