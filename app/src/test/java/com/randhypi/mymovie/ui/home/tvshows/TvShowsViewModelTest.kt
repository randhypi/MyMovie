package com.randhypi.mymovie.ui.home.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest{


    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>


    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(moviesRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        tv.value = dummyTv

        `when`(moviesRepository.getTvShows()).thenReturn(tv)
        val tvEntities = viewModel.getTvShows()?.value
        verify(moviesRepository).getTvShows()
        assertEquals(tv?.value?.data?.size, tvEntities?.data?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

}