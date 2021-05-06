package com.randhypi.mymovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.randhypi.mymovie.data.source.local.LocalDataSource
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailMovie
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.utils.AppExecutors
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import com.randhypi.mymovie.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@Suppress("UNCHECKED_CAST")
class MoviesRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

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
        ResponseDetailMovie(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            title = it?.title,
            posterPath = it?.poster,
            releaseDate = it?.releaseDate,
            popularity = it?.popularity,
            id = it?.moviesId!!.toInt())
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
        ResponseDetailTv(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalName = it?.originalName,
            name = it?.name,
            posterPath = it?.poster,
            firstAirDate = it?.date,
            popularity = it?.popularity,
            id = it?.id!!.toInt())
    }.first()


    @Test
    fun getAllMovies() {
        val dummyMovies = MutableLiveData<List<MoviesEntity>>()
        dummyMovies.value = DummyMovies.moviesDummy()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val moviesEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getMovies())
        verify(local).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size, moviesEntities.data?.size)


    }

    @Test
    fun getAllTv() {
        val dummyTv = MutableLiveData<List<TvShowsEntity>>()
        dummyTv.value = DummyTvShows.tvShowsDummy()
        `when`(local.getAllTv()).thenReturn(dummyTv)

        val tvEntities = LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getTvShows())
        verify(local).getAllTv()

        assertNotNull(tvEntities)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyMovies = MutableLiveData<MoviesEntity>()
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
        val dummyTv = MutableLiveData<TvShowsEntity>()
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
        val dummyMovies = MutableLiveData<List<MoviesEntity>>()
        dummyMovies.value = DummyMovies.moviesDummy()
        `when`(local.getFavMovie()).thenReturn(dummyMovies)

        val moviesEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getFavMovies())
        verify(local).getFavMovie()
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size, moviesEntities.size)
    }

    @Test
    fun getFavTv() {
        val dummyTv = MutableLiveData<List<TvShowsEntity>>()
        dummyTv.value = DummyTvShows.tvShowsDummy()
        `when`(local.getFavTv()).thenReturn(dummyTv)

        val tvEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getFavTvShows())
        verify(local).getFavTv()
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size, tvEntities.size)
    }


}