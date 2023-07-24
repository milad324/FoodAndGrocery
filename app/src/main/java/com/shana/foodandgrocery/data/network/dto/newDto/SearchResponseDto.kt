package com.shana.foodandgrocery.data.network.dto.newDto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponseDto(
    @Json(name = "number")
    val number: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val recipes: List<Recipe>,
    @Json(name = "totalResults")
    val totalResults: Int
)