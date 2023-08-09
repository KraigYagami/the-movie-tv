package com.example.themovietv.modules.home.data.dto

import com.example.themovietv.modules.home.domain.model.MovieModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
) {
    fun toDomain() = MovieModel(
        title = title,
        releaseDate = releaseDate,
        summary = overview,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
        backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" } ?: ""
    )
}
