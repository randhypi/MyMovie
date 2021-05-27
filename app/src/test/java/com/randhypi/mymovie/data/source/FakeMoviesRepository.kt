package com.randhypi.mymovie.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.capstone.core.utils.AppExecutors
import com.capstone.core.data.Resource
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity

class FakeMoviesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.capstone.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) :
    com.capstone.core.data.MoviesDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            com.capstone.core.data.NetworkBoundResource<PagedList<com.capstone.core.data.source.local.entity.MoviesEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<com.capstone.core.data.source.local.entity.MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()

            }


            override fun shouldFetch(data: PagedList<com.capstone.core.data.source.local.entity.MoviesEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<com.capstone.core.data.source.remote.ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovies()


            override fun saveCallResult(data: List<ResultsItem>) {
                val moviesArrayList = ArrayList<com.capstone.core.data.source.local.entity.MoviesEntity>()
                for (response in data) {
                    val id = response?.id.toString()
                    val original_language = response?.originalLanguage
                    val original_title = response?.originalTitle
                    val title = response?.title
                    val release_date = response?.releaseDate
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                    val overview = response?.overview
                    val popularity = response?.popularity

                    var movie = com.capstone.core.data.source.local.entity.MoviesEntity(
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

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object :
            com.capstone.core.data.NetworkBoundResource<PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>, List<ResultsItemTv>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTv(), config).build()
            }


            override fun shouldFetch(data: PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<com.capstone.core.data.source.remote.ApiResponse<List<ResultsItemTv>>> =
                remoteDataSource.getTv()


            override fun saveCallResult(data: List<ResultsItemTv>) {
                val tvList = ArrayList<com.capstone.core.data.source.local.entity.TvShowsEntity>()
                for (response in data) {
                    val id = response?.id!!.toString()
                    val original_language = response?.originalLanguage!!
                    val original_title = response?.originalName!!
                    val title = response?.name!!
                    val release_date = response?.firstAirDate!!
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath!!}"
                    val overview = response?.overview!!
                    val popularity = response?.voteAverage!!

                    var tv = com.capstone.core.data.source.local.entity.TvShowsEntity(
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

    override fun getDetailMovies(id: String): LiveData<com.capstone.core.data.source.local.entity.MoviesEntity> =
        localDataSource.getDetailMovie(id)

    override fun getDetailTvShows(id: String): LiveData<com.capstone.core.data.source.local.entity.TvShowsEntity> =
        localDataSource.getDetailTv(id)

    override fun getFavMovies(): LiveData<PagedList<com.capstone.core.data.source.local.entity.MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()

    }


    override fun getFavTvShows(): LiveData<PagedList<com.capstone.core.data.source.local.entity.TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()

    }

    override fun setFavMovie(movie: com.capstone.core.data.source.local.entity.MoviesEntity) =
        appExecutors.diskIO().execute { localDataSource.updateMovie(movie) }

    override fun setFavTvShow(tv: com.capstone.core.data.source.local.entity.TvShowsEntity) =
        appExecutors.diskIO().execute { localDataSource.updateTv(tv) }


}