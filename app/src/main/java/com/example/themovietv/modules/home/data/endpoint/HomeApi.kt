package com.example.themovietv.modules.home.data.endpoint

import com.example.themovietv.modules.home.data.dto.DiscoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DISCOVER_MOVIE = "discover/movie"

interface HomeApi {

    @GET(DISCOVER_MOVIE)
    suspend fun getDiscoverMovies(
        @Query("sort_by") sortBy: String,
        @Query("vote_count.gte") voteCount: Int = 100
    ): Response<DiscoverResponse>
}
