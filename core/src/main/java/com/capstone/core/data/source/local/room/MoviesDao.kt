package com.capstone.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity



@Dao
interface MoviesDao {
    @Query("SELECT * FROM moviesentity")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Query("SELECT * FROM tvshowsentity")
    fun getTvShows():DataSource.Factory<Int, TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tv: List<TvShowsEntity>)

    @Query("SELECT * FROM moviesentity WHERE moviesId = :moviesId")
    fun getDetailMovies(moviesId: String):LiveData<MoviesEntity>

    @Query("SELECT * FROM tvshowsentity WHERE tvShowsId = :tvshowsId")
    fun getDetailTvShow(tvshowsId: String): LiveData<TvShowsEntity>

    @Query("SELECT * FROM moviesentity WHERE favorite = :favorite")
    fun getFavMovie(favorite: Boolean = true): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tvshowsentity WHERE favorite = :favorite")
    fun getFavTvShow(favorite: Boolean = true): DataSource.Factory<Int, TvShowsEntity>

    @Update()
    fun updateMovie(movies: MoviesEntity)

    @Update()
    fun updateTv(tv: TvShowsEntity)


}