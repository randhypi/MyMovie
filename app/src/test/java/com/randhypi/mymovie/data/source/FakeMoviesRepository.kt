package com.randhypi.mymovie.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.randhypi.mymovie.data.source.local.LocalDataSource
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.remote.ApiResponse
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.utils.AppExecutors
import com.randhypi.mymovie.vo.Resource

class FakeMoviesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MoviesDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()

            }


            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
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

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowsEntity>, List<ResultsItemTv>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTv(), config).build()
            }


            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
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

    override fun getDetailMovies(id: String): LiveData<MoviesEntity> =
        localDataSource.getDetailMovie(id)

    override fun getDetailTvShows(id: String): LiveData<TvShowsEntity> =
        localDataSource.getDetailTv(id)

    override fun getFavMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()

    }


    override fun getFavTvShows(): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()

    }

    override fun setFavMovie(movie: MoviesEntity) =
        appExecutors.diskIO().execute { localDataSource.updateMovie(movie) }

    override fun setFavTvShow(tv: TvShowsEntity) =
        appExecutors.diskIO().execute { localDataSource.updateTv(tv) }


}