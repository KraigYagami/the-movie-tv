package com.example.themovietv.modules.home.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DiscoverResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val dataResponses: List<DataResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
