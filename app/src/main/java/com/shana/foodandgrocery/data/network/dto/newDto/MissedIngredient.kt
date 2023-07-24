package com.shana.foodandgrocery.data.network.dto.newDto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissedIngredient(
    @Json(name = "aisle")
    val aisle: String,
    @Json(name = "amount")
    val amount: Double,
    @Json(name = "extendedName")
    val extendedName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "meta")
    val meta: List<String>,
    @Json(name = "name")
    val name: String,
    @Json(name = "original")
    val original: String,
    @Json(name = "originalName")
    val originalName: String,
    @Json(name = "unit")
    val unit: String,
    @Json(name = "unitLong")
    val unitLong: String,
    @Json(name = "unitShort")
    val unitShort: String
)