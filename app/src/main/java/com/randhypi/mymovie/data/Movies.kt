package com.randhypi.mymovie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movies(
    var id: String? = "",
    var original_language: String? = "",
    var original_title: String? = "",
    var title: String? = "",
    var release_date: String? = "",
    var poster: String? = "",
    var overview: String? = "",
    var popularity: Double? = null
): Parcelable
