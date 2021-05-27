package com.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShows(
    val id: String = "",
    val name: String = "",
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double,
    val poster: String = "",
    val date: String? = "",
    var favorite: Boolean? = false
): Parcelable
