package com.randhypi.mymovie.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "moviesentity")
data class MoviesEntity(
    @PrimaryKey
    @ColumnInfo(name = "moviesId")
    var moviesId: String = "",

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String? = "",

    @ColumnInfo(name="originalTitle")
    var originalTitle: String? = "",

    @ColumnInfo(name="title")
    var title: String? = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = "",

    @ColumnInfo(name = "poster")
    var poster: String? = "",

    @ColumnInfo(name = "overview")
    var overview: String? = "",

    @ColumnInfo(name = "popularity")
    var popularity: Double? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = false
): Parcelable
