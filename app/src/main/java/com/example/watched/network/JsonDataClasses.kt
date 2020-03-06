package com.example.watched.network

import com.example.watched.domain.Show
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkShow(
    val id: Int,
    val url: String?,
    val name: String,
    val type: String?,
    val language: String?,
    val genres: List<String>?,
    val status: String?,
    val runtime: Int?,
    val premiered: String?,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Rating?,
    val weight: Int?,
    val network: TvNetwork?,
    val webChannel: WebChannel?,
    val externals: Externals?,
    val image: Image?,
    val summary: String?,
    val updated: Int?,
    @Json(name = "_links")
    val links: Links?
)

fun List<NetworkShow>.asDomainModel(): List<Show> {
    return map {
        Show(
            id = it.id,
            name = it.name,
            status = it.status,
            runtime = it.runtime,
            premiered = it.premiered,
            officialSite = it.officialSite,
            image = it.image?.original,
            imageMedium = it.image?.medium,
            summary = it.summary,
            seasons = null
        )
    }
}

@JsonClass(generateAdapter = true)
data class NetworkEpisode(
    val id: Number,
    val url: String?,
    val name: String?,
    val season: Number?,
    val number: Number?,
    val airdate: String?,
    val airtime: String?,
    val airstamp: String?,
    val runtime: Number?,
    val image: Image?,
    val summary: String?,
    @Json(name = "_links")
    val links: Links?
)

@JsonClass(generateAdapter = true)
data class SearchResult(
    val score: Float,
    val show: NetworkShow
)



@JsonClass(generateAdapter = true)
data class Country(val name: String?, val code: String?, val timezone: String?)

@JsonClass(generateAdapter = true)
data class Externals(val tvrage: Int?, val thetvdb: Int?, val imdb: String?)

@JsonClass(generateAdapter = true)
data class Image(val medium: String?, val original: String?)

@JsonClass(generateAdapter = true)
data class TvNetwork(val id: Int?, val name: String?, val country: Country?)

@JsonClass(generateAdapter = true)
data class PreviousEpisode(val href: String?)

@JsonClass(generateAdapter = true)
data class Rating(val average: Float?)

@JsonClass(generateAdapter = true)
data class Schedule(val time: String?, val days: List<String>?)

@JsonClass(generateAdapter = true)
data class Self(val href: String?)

@JsonClass(generateAdapter = true)
data class WebChannel(val id: Int?, val name: String?, val country: Country?)

@JsonClass(generateAdapter = true)
data class Links(val self: Self?, val previousEpisode: PreviousEpisode?)
