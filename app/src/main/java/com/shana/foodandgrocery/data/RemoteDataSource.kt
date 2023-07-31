package com.shana.foodandgrocery.data

import com.shana.foodandgrocery.data.network.FoodRecipesApi
import com.shana.foodandgrocery.data.network.dto.newDto.SearchResponseDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(
        queries: Map<String, String>,
    ): SearchResponseDto {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): SearchResponseDto {
        return foodRecipesApi.searchRecipes(searchQuery)
    }


}