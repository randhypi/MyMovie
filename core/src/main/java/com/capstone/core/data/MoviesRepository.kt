package com.capstone.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.capstone.core.data.source.local.LocalDataSource
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.data.source.remote.ApiResponse
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows
import com.capstone.core.domain.repository.IMoviesRepository
import com.capstone.core.utils.AppExecutors
import com.capstone.core.utils.DataMapper
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import io.reactivex.Flowable

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :  IMoviesRepository {


    override fun getMovies(): Flowable<Resource<List<Movies>>> {
        return object :
            NetworkBoundResource<List<Movies>, List<ResultsItem>>() {
            override fun loadFromDB(): Flowable<List<Movies>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomainMovies(it) }

            }


            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovies()


            override fun saveCallResult(data: List<ResultsItem>) {
                val moviesArrayList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val id = response?.id.toString()
                    val original_language = response?.originalLanguage
                    val original_title = response?.originalTitle
                    val title = response?.title
                    val release_date = response?.releaseDate
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                    val overview = response?.overview
                    val popularity = response?.popularity

                    var movie = MoviesEntity(
                        id,
                        original_language,
                        original_title,
                        title, release_date,
                        poster,
                        overview,
                        popularity
                    )

                    moviesArrayList.add(movie)
                }
                localDataSource.insertMovies(moviesArrayList)
            }


        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShows>>> {
        return object :
            NetworkBoundResource<PagedList<TvShows>, List<ResultsItemTv>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShows>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                val data = localDataSource.getAllTv().map {
                    DataMapper.mapEntitiesToDomainTvShows(it)
                }

                return LivePagedListBuilder(data, config).build()
            }


            override fun shouldFetch(data: PagedList<TvShows>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemTv>>> =
                remoteDataSource.getTv()


            override fun saveCallResult(data: List<ResultsItemTv>) {
                val tvList = ArrayList<TvShowsEntity>()
                for (response in data) {
                    val id = response?.id!!.toString()
                    val original_language = response?.originalLanguage!!
                    val original_title = response?.originalName!!
                    val title = response?.name!!
                    val release_date = response?.firstAirDate!!
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath!!}"
                    val overview = response?.overview!!
                    val popularity = response?.voteAverage!!

                    var tv = TvShowsEntity(
                        id = id,
                        name = title,
                        originalName = original_title,
                        originalLanguage = original_language,
                        overview = overview,
                        popularity = popularity,
                        poster = poster,
                        date = release_date
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTvs(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovies(id: String): LiveData<Movies> =
        localDataSource.getDetailMovie(id).map {
            DataMapper.mapEntitiesToDomainMovies(it)
        }


    override fun getDetailTvShows(id: String): LiveData<TvShows> =
        localDataSource.getDetailTv(id).map {
            DataMapper.mapEntitiesToDomainTvShows(it)
        }


    override fun getFavMovies(): LiveData<PagedList<Movies>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        val data = localDataSource.getFavMovie().map {
            DataMapper.mapEntitiesToDomainMovies(it)
        }

        return LivePagedListBuilder(data, config).build()

    }


    override fun getFavTvShows(): LiveData<PagedList<TvShows>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        val data = localDataSource.getFavTv().map {
            DataMapper.mapEntitiesToDomainTvShows(it)
        }


        return LivePagedListBuilder(data, config).build()

    }

    override fun setFavMovie(movie: Movies) {
        val movieEntity = DataMapper.mapDomainToEntitiesMovies(movie)

        appExecutors.diskIO().execute {

            localDataSource.updateMovie(movieEntity)
        }

    }

    override fun setFavTvShow(tv: TvShows) {
     val tvShowsEntity = DataMapper.mapDomainToEntitiesTvShows(tv)
        Log.d("Movies Repository", tvShowsEntity.toString())
        appExecutors.diskIO().execute {
            localDataSource.updateTv(tvShowsEntity)
        }

    }


}