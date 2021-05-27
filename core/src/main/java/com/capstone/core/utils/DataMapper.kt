package com.capstone.core.utils

import androidx.paging.PagedList
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows

object DataMapper {

    fun mapEntitiesToDomainMovies(it: MoviesEntity): Movies=

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



    fun mapDomainToEntitiesMovies(it: Movies): MoviesEntity =
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

    fun mapEntitiesToDomainTvShows(it: TvShowsEntity): TvShows =

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


    fun mapDomainToEntitiesTvShows(it: TvShows): TvShowsEntity =
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
