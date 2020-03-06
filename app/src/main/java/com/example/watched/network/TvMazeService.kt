package com.example.watched.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.tvmaze.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TvMazeService {
    @GET("shows/{id}")
    suspend fun getShow(@Path("id") id: Int): NetworkShow

    @GET("/search/shows")
    suspend fun searchShows(@Query("q") query: String): List<SearchResult>

    @GET("/shows/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id: Int): List<NetworkEpisode>

}

object Network {
    val tvMaze: TvMazeService by lazy {
        retrofit.create(TvMazeService::class.java)
    }
}