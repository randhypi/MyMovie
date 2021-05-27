package com.capstone.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movies(
var moviesId: String = "",
var originalLanguage: String? = "",
var originalTitle: String? = "",
var title: String? = "",
var releaseDate: String? = "",
var poster: String? = "",
var overview: String? = "",
var popularity: Double? = null,
var favorite: Boolean? = false
): Parcelable
