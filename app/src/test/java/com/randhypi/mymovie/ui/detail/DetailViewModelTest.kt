package com.randhypi.mymovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DummyMovies.moviesDummy()[0]
    private val moviesId = dummyMovies.moviesId
    private val dummyTvShows = DummyTvShows.tvShowsDummy()[0]
    private val tvShowsId = dummyTvShows.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var moviesObserver: Observer<MoviesEntity>

    @Mock
    lateinit var moviesFavObserver: Observer<Boolean>

    @Mock
    lateinit var tvFavObserver: Observer<Boolean>

    @Mock
    private lateinit var tvObserver: Observer<TvShowsEntity>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(moviesId)
    }


    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<MoviesEntity>()
        movies.value = dummyMovies

        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(moviesId)

        `when`(moviesRepository.getDetailMovies(moviesId!!)).thenReturn(movies)
        viewModel.getDetailMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Test
    fun getDetailTvSHows() {
        val tv = MutableLiveData<TvShowsEntity>()
        tv.value = dummyTvShows
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(tvShowsId)


        `when`(moviesRepository.getDetailTvShows(tvShowsId)).thenReturn(tv)

        viewModel.getDetailTvSHows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShows)
    }

    @Test
    fun setMoviesFav() {
        val movies = dummyMovies
        movies.let { it.favorite = !it.favorite!! }


        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(moviesId)

        doNothing().`when`(moviesRepository).setFavMovie(movies)

        viewModel.setFavMovie(movies)
        verify(moviesRepository).setFavMovie(movies)


    }

    @Test
    fun setTvShowsFav() {
        val tv = dummyTvShows
        tv.let {
            it?.favorite = !it?.favorite!!
        }
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(tvShowsId)

        doNothing().`when`(moviesRepository).setFavTvShow(tv)
        viewModel.setFavTvShow(tv)
        verify(moviesRepository).setFavTvShow(tv)

    }


    @Test
    fun getMoviesFav() {
        var value = MutableLiveData<Boolean>()
        value.value = true
        viewModel = DetailViewModel(moviesRepository)

        viewModel.moviesFav.value = value.value
        viewModel.getMovieFav().observeForever(moviesFavObserver)

        verify(moviesFavObserver).onChanged(value.value)
    }

    @Test
    fun getTvFav() {
        var value = MutableLiveData<Boolean>()
        value.value = true
        viewModel = DetailViewModel(moviesRepository)

        viewModel.tvFav.value = value.value
        viewModel.getTvFav().observeForever(tvFavObserver)

        verify(tvFavObserver).onChanged(value.value)
    }
}