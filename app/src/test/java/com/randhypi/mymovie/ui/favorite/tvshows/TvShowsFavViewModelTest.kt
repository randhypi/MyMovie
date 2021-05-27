package com.randhypi.mymovie.ui.favorite.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.randhypi.mymovie.ui.home.tvshows.TvShowsFavViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsFavViewModelTest{

private lateinit var viewModel: TvShowsFavViewModel

@get:Rule
var instantTaskExecutorRule = InstantTaskExecutorRule()

@Mock
private lateinit var observer: Observer<PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>>


@Mock
private lateinit var moviesRepository: com.capstone.core.data.MoviesRepository

@Mock
private lateinit var pagedList: PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>

@Before
fun setUp() {
    viewModel = TvShowsFavViewModel(moviesRepository)
}

@Test
fun getTvFav() {
    val dummyTv = pagedList
   `when`(dummyTv?.size).thenReturn(5)
    val tv = MutableLiveData<PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>>()
    tv.value = dummyTv

   `when`(moviesRepository.getFavTvShows()).thenReturn(tv)
    val moviesEntities = viewModel.getTvShowsFav()?.value

    verify<com.capstone.core.data.MoviesRepository>(moviesRepository, Mockito.times(1)).getFavTvShows()
    assertEquals(5, moviesEntities?.size)

    viewModel.getTvShowsFav()?.observeForever(observer)
    verify(observer).onChanged(dummyTv)


}

}