package com.randhypi.mymovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailMovie
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.utils.DummyTvShows
import com.randhypi.mymovie.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


@Suppress("UNCHECKED_CAST")
class MoviesRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)

    private val moviesRepository = FakeMoviesRepository(remote)

    private val moviesId = DummyMovies.moviesDummy()[0].id
    private val moviesResponse = DummyMovies.moviesDummy().map {
        ResultsItem(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            title = it?.title,
            posterPath = it?.poster,
            releaseDate = it?.releaseDate,
            popularity = it?.popularity,
            id = it?.id!!.toInt())
    }
    private val moviesDetailResponse = DummyMovies.moviesDummy().map{
        ResponseDetailMovie(overview = it?.overview,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            title = it?.title,
            posterPath = it?.poster,
            releaseDate = it?.releaseDate,
            popularity = it?.popularity,
            id = it?.id!!.toInt())
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
        doAnswer{ invocaton ->
            (invocaton.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(remote).getMovies(any())
        val moviesEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.size.toLong())


    }

    @Test
    fun getAllTv() {
        doAnswer { invocaton ->
            (invocaton.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(tvResponse)
            null
        }.`when`(remote).getTv(any())

        val tvEntities = LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getTvShows())
        verify(remote).getTv(any())

        assertNotNull(tvEntities)
        assertEquals(tvResponse.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMoviesCallback)
                .onMoviesReceived(moviesDetailResponse)
            null
        }.`when`(remote).getDetailMovies(any(), eq(moviesId!!))

        val moviesDetailEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getDetailMovies(moviesId))

        verify(remote)
            .getDetailMovies(any(), eq(moviesId))

        assertNotNull(moviesDetailEntities)
        assertEquals(DummyMovies.moviesDummy()[0].id, moviesDetailEntities.id)
    }

    @Test
    fun getDetailTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvCallback)
                .onTvReceived(tvDetailResponse)
            null
        }.`when`(remote).getDetailTv(any(), eq(tvId!!))

        val tvDetailEntities =
            LiveDataTestUtil.LiveDataTestUtil.getValue(moviesRepository.getDetailTvShows(tvId!!))

        verify(remote)
            .getDetailTv(any(), eq(tvId))

        assertNotNull(tvDetailEntities)
        assertEquals(DummyTvShows.tvShowsDummy()[0], tvDetailEntities)
    }


}