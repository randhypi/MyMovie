package com.randhypi.mymovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity


@Dao
interface MoviesDao {
    @Query("SELECT * FROM moviesentity")
    fun getMovies(): LiveData<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Query("SELECT * FROM tvshowsentity")
    fun getTvShows():LiveData<List<TvShowsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tv: List<TvShowsEntity>)

    @Query("SELECT * FROM moviesentity WHERE moviesId = :moviesId")
    fun getDetailMovies(moviesId: String):LiveData<MoviesEntity>

    @Query("SELECT * FROM tvshowsentity WHERE tvShowsId = :tvshowsId")
    fun getDetailTvShow(tvshowsId: String): LiveData<TvShowsEntity>

    @Query("SELECT * FROM moviesentity WHERE favorite = :favorite")
    fun getFavMovie(favorite: Boolean = true): LiveData<List<MoviesEntity>>

    @Update()
    fun updateMovie(movies: MoviesEntity)

    @Query("SELECT * FROM tvshowsentity WHERE favorite = :favorite")
    fun getFavTvShow(favorite: Boolean = true): LiveData<List<TvShowsEntity>>

    @Update()
    fun updateTv(tv: TvShowsEntity)


}