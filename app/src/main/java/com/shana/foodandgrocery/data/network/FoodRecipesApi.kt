package com.shana.foodandgrocery.data.network


import com.shana.foodandgrocery.data.network.dto.newDto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.Objects

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): SearchResponseDto

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQueries: Map<String, String>
    ): SearchResponseDto

}