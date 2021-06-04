package com.capstone.core.utils

import androidx.paging.PagedList
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows

object DataMapper {

    fun mapEntitiesToDomainMovies(it: List<MoviesEntity>): List<Movies> {

        val movieList = ArrayList<Movies>()

        it.map {
            val movies = Movies(
                moviesId = it.moviesId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                releaseDate = it.releaseDate,
                poster = it.poster,
                overview = it.overview,
                popularity = it.popularity,
                favorite = it.favorite
            )

            movieList.add(movies)
        }
        return movieList
    }


    fun mapDomainToEntitiesMovies(it: List<Movies>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()

        it.map {
            val movies = MoviesEntity(
                moviesId = it.moviesId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                releaseDate = it.releaseDate,
                poster = it.poster,
                overview = it.overview,
                popularity = it.popularity,
                favorite = it.favorite
            )
            moviesList.add(movies)
        }
        return moviesList
    }


    fun mapEntitiesToDomainTvShows(it: List<TvShowsEntity>): List<TvShows> {
        val tvShowsList = ArrayList<TvShows>()

        it.map {
            tvShowsList.add(
                TvShows(
                    id = it.id,
                    name = it.name,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster = it.poster,
                    date = it.date,
                    favorite = it.favorite
                )
            )
        }
        return tvShowsList


    }


    fun mapDomainToEntitiesTvShows(it: List<TvShows>): List<TvShowsEntity> {
        val tvShowsList = ArrayList<TvShowsEntity>()

        it.map{
            tvShowsList.add(
                TvShowsEntity(
                    id = it.id,
                    name = it.name,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster = it.poster,
                    date = it.date,
                    favorite = it.favorite
                )
            )
        }
        return  tvShowsList
    }

    fun mapDetailEntitiesToDomainMovies(it: MoviesEntity): Movies=

        Movies(
            moviesId = it.moviesId,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            title = it.title,
            releaseDate = it.releaseDate,
            poster = it.poster,
            overview = it.overview,
            popularity = it.popularity,
            favorite = it.favorite
        )

    fun mapDetailDomainToEntitiesMovies(it: Movies): MoviesEntity =
        MoviesEntity(
            moviesId = it.moviesId,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            title = it.title,
            releaseDate = it.releaseDate,
            poster = it.poster,
            overview = it.overview,
            popularity = it.popularity,
            favorite = it.favorite
        )


    fun mapDetailEntitiesToDomainTvShows(it: TvShowsEntity): TvShows =

        TvShows(
            id = it.id,
            name = it.name,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            poster = it.poster,
            date = it.  date,
            favorite = it.favorite
        )


    fun mapDetailDomainToEntitiesTvShows(it: TvShows): TvShowsEntity =
        TvShowsEntity(
            id = it.id,
            name = it.name,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            poster = it.poster,
            date = it.date,
            favorite = it.favorite
        )

}
