package com.randhypi.mymovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "tvshowsentity")
data class TvShowsEntity(
    @PrimaryKey
    @ColumnInfo(name = "tvShowsId")
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String = "",
    @ColumnInfo(name = "originalName")
    val originalName: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster")
    val poster: String = "",
    @ColumnInfo(name = "date")
    val date: String? = "",
    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = false
) : Parcelable
