package com.randhypi.mymovie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TvShows(
    val id: String = "",
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double,
    val poster: String = "",
    val date: String? = ""
):Parcelable
