package com.randhypi.mymovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import org.junit.Test
import org.junit.Assert.*
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
    private lateinit var tvObserver: Observer<TvShowsEntity>


    @Before
    fun setUp(){
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(moviesId)
    }


    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<MoviesEntity>()
        movies.value = dummyMovies
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setIdAndType(moviesId)

        `when`(moviesRepository.getDetailMovies(moviesId)).thenReturn(movies)
        val moviesEntity = viewModel.getDetailMovies().value

        verify(moviesRepository).getDetailMovies(moviesId)

        assertNotNull(moviesEntity)

        assertEquals(dummyMovies.poster,moviesEntity?.poster)
        assertEquals(dummyMovies.moviesId,moviesEntity?.moviesId)
        assertEquals(dummyMovies.title,moviesEntity?.title)

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
        val tvEntity = viewModel.getDetailTvSHows().value

        verify(moviesRepository).getDetailTvShows(tvShowsId)

        assertNotNull(tvEntity)

        assertEquals(dummyTvShows.poster,tvEntity?.poster)
        assertEquals(dummyTvShows.id,tvEntity?.id)
        assertEquals(dummyTvShows.name,tvEntity?.name)

        viewModel.getDetailTvSHows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShows)
    }

}