package com.randhypi.mymovie.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.randhypi.mymovie.data.MoviesDataSource
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailMovie
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv

class FakeMoviesRepository(private val remoteDataSource: RemoteDataSource) :
    MoviesDataSource {

    override fun getMovies(): LiveData<List<MoviesEntity>> {
        val listMovies = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesRsponse: List<ResultsItem?>) {
                val moviesArrayList = ArrayList<MoviesEntity>()
                for (response in moviesRsponse) {
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
                listMovies.postValue(moviesArrayList)
            }
        })
        return listMovies
    }

    override fun getTvShows(): LiveData<List<TvShowsEntity>> {
        val listTv = MutableLiveData<List<TvShowsEntity>>()
        remoteDataSource.getTv(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvShowsResponse: List<ResultsItemTv?>) {
                val tvList = ArrayList<TvShowsEntity>()
                for (response in tvShowsResponse) {
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
                listTv.postValue(tvList)
            }
        })
        return listTv
    }

    override fun getDetailMovies(moviesId: String): LiveData<MoviesEntity> {
        val moviesResult = MutableLiveData<MoviesEntity>()

        remoteDataSource.getDetailMovies(
            id = moviesId,
            callback = object : RemoteDataSource.LoadDetailMoviesCallback {
                override fun onMoviesReceived(detailMovie: ResponseDetailMovie?) {
                    lateinit var movie: MoviesEntity

                    val response = detailMovie

                    val id = response?.id.toString()
                    val original_language = response?.originalLanguage
                    val original_title = response?.title
                    val title = response?.title
                    val release_date = response?.releaseDate
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                    val overview = response?.overview
                    val popularity = response?.popularity

                    movie = MoviesEntity(
                        id,
                        original_language,
                        original_title,
                        title,
                        release_date,
                        poster,
                        overview,
                        popularity
                    )
                    moviesResult.postValue(movie)
                }
            })
        return moviesResult
    }


    override fun getDetailTvShows(id: String): LiveData<TvShowsEntity> {
        val tvResult = MutableLiveData<TvShowsEntity>()

        remoteDataSource.getDetailTv(id =id,callback = object: RemoteDataSource.LoadDetailTvCallback{
            override fun onTvReceived(detailTvResponse: ResponseDetailTv?) {
               lateinit var tvShows: TvShowsEntity
               val response = detailTvResponse

                val id = response?.id!!.toString()
                val original_language = response?.originalLanguage!!
                val original_title = response?.originalName!!
                val title = response?.name!!
                val release_date = response?.firstAirDate!!
                val poster = response.posterPath!!
                val overview = response.overview!!
                val popularity = response.popularity!!

                tvShows = TvShowsEntity(
                    id = id,
                    name = title,
                    originalName = original_title,
                    originalLanguage = original_language,
                    overview = overview,
                    popularity = popularity,
                    poster = poster,
                    date = release_date
                )
                tvResult.postValue(tvShows)
            }
        })
        return tvResult
    }


}