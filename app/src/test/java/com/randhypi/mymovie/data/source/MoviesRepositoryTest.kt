package com.randhypi.mymovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.randhypi.mymovie.data.FakeMoviesRepository
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.capstone.core.utils.AppExecutors
import com.capstone.core.utils.DummyMovies
import com.capstone.core.utils.DummyTvShows
import com.capstone.core.utils.TestExecutor
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.utils.*
import com.capstone.core.data.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


@Suppress("UNCHECKED_CAST")
class MoviesRepositoryTest {


    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(com.capstone.core.data.source.local.LocalDataSource::class.java)
    private val appExecutors: AppExecutors = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val moviesRepository = FakeMoviesRepository(remote,local,appExecutors)



    private val moviesId = DummyMovies.moviesDummy()[0].moviesId
    private val moviesResponse = DummyMovies.moviesDummy().map {
        ResultsItem(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            title = it?.title,
            posterPath = it?.poster,
            releaseDate = it?.releaseDate,
            popularity = it?.popularity,
            id = it?.moviesId!!.toInt())
    }
    private val moviesDetailResponse = DummyMovies.moviesDummy().map{
        com.capstone.core.data.source.remote.response.ResponseDetailMovie(
            overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            title = it?.title,
            posterPath = it?.poster,
            releaseDate = it?.releaseDate,
            popularity = it?.popularity,
            id = it?.moviesId!!.toInt()
        )
    }.first()
    private val tvId = DummyTvShows.tvShowsDummy()[0].id
    private val tvResponse = DummyTvShows.tvShowsDummy().map {
        ResultsItemTv(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalName = it?.originalName,
            name = it?.name,
            posterPath = it?.poster,
            firstAirDate = it?.date,
            voteAverage = it?.popularity,
            id = it?.id!!.toInt())
    }
    private  val tvDetailResponse = DummyTvShows.tvShowsDummy().map{
        com.capstone.core.data.source.remote.response.ResponseDetailTv(
            overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalName = it?.originalName,
            name = it?.name,
            posterPath = it?.poster,
            firstAirDate = it?.date,
            popularity = it?.popularity,
            id = it?.id!!.toInt()
        )
    }.first()


    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, com.capstone.core.data.source.local.entity.MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)

        moviesRepository.getMovies()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DummyMovies.moviesDummy()))
        verify(local).getAllMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, com.capstone.core.data.source.local.entity.TvShowsEntity>
        `when`(local.getAllTv()).thenReturn(dataSourceFactory)
        moviesRepository.getTvShows()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyTvShows.tvShowsDummy()))
        verify(local).getAllTv()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyMovies = MutableLiveData<com.capstone.core.data.source.local.entity.MoviesEntity>()
        dummyMovies.value = DummyMovies.moviesDummy()[0]
        `when`(local.getDetailMovie(moviesId)).thenReturn(dummyMovies)

        val moviesDetailEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getDetailMovies(moviesId))

        verify(local).getDetailMovie(moviesId)

        assertNotNull(moviesDetailEntities)
        assertEquals(moviesDetailEntities.moviesId, moviesDetailResponse.id.toString())
    }

    @Test
    fun getDetailTv() {
        val dummyTv = MutableLiveData<com.capstone.core.data.source.local.entity.TvShowsEntity>()
        dummyTv.value = DummyTvShows.tvShowsDummy()[0]
        `when`(local.getDetailTv(tvId)).thenReturn(dummyTv)

        val tvDetailEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getDetailTvShows(tvId))

        verify(local).getDetailTv(tvId)

        assertNotNull(tvDetailEntities)
        assertEquals(tvDetailResponse.id.toString(), tvDetailEntities.id)
    }

    @Test
    fun getFavMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, com.capstone.core.data.source.local.entity.MoviesEntity>
        `when`(local.getFavMovie()).thenReturn(dataSourceFactory)
        moviesRepository.getFavMovies()

        val moviesEntities =
            PagedListUtil.mockPagedList(DummyMovies.moviesDummy())

        verify(local).getFavMovie()
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size, moviesEntities.size)
    }

    @Test
    fun getFavTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, com.capstone.core.data.source.local.entity.TvShowsEntity>
        `when`(local.getFavTv()).thenReturn(dataSourceFactory)
        moviesRepository.getFavTvShows()

        val tvEntities =
            PagedListUtil.mockPagedList(DummyTvShows.tvShowsDummy())
        verify(local).getFavTv()
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size, tvEntities.size)
    }

    @Test
    fun setFavMovies(){
        val dataDummy = DummyMovies.moviesDummy()[0]
          dataDummy.let {
            it.favorite =false
        }

        moviesRepository.setFavMovie(dataDummy)
        verify(local).updateMovie(dataDummy)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavTvShows(){
        val dataDummy = DummyTvShows.tvShowsDummy()[0]
        dataDummy.let {
            it.favorite =false
        }

        moviesRepository.setFavTvShow(dataDummy)
        verify(local).updateTv(dataDummy)
        verifyNoMoreInteractions(local)
    }
}