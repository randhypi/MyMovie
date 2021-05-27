package com.capstone.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDetailTv(

    @field:SerializedName("original_language")
	val originalLanguage: String? = null,

    @field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int? = null,

    @field:SerializedName("networks")
	val networks: List<NetworksItemTv?>? = null,

    @field:SerializedName("type")
	val type: String? = null,

    @field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,


    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int? = null,

    @field:SerializedName("vote_count")
	val voteCount: Int? = null,

    @field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

    @field:SerializedName("overview")
	val overview: String? = null,

    @field:SerializedName("seasons")
	val seasons: List<SeasonsItemTv?>? = null,

    @field:SerializedName("languages")
	val languages: List<String?>? = null,

    @field:SerializedName("created_by")
	val createdBy: List<CreatedByItemTv?>? = null,

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("origin_country")
	val originCountry: List<String?>? = null,

    @field:SerializedName("original_name")
	val originalName: String? = null,

    @field:SerializedName("vote_average")
	val voteAverage: Double? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("tagline")
	val tagline: String? = null,

    @field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int?>? = null,

    @field:SerializedName("in_production")
	val inProduction: Boolean? = null,

    @field:SerializedName("last_air_date")
	val lastAirDate: String? = null,

    @field:SerializedName("homepage")
	val homepage: String? = null,

    @field:SerializedName("status")
	val status: String? = null
) : Parcelable




@Parcelize
data class CreatedByItemTv(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class NetworksItemTv(

	@field:SerializedName("logo_path")
	val logoPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("origin_country")
	val originCountry: String? = null
) : Parcelable

@Parcelize
data class SeasonsItemTv(

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("episode_count")
	val episodeCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
) : Parcelable



