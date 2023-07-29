package com.shana.foodandgrocery.data.network.dto.newDto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
    @Json(name = "aggregateLikes")
    val aggregateLikes: Int,
//    @Json(name = "analyzedInstructions")
//    val analyzedInstructions: List<AnalyzedInstruction>?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "cheap")
    val cheap: Boolean,
    @Json(name = "cookingMinutes")
    val cookingMinutes: Int,
    @Json(name = "creditsText")
    val creditsText: String,
    @Json(name = "cuisines")
    val cuisines: List<String>,
    @Json(name = "dairyFree")
    val dairyFree: Boolean,
    @Json(name = "diets")
    val diets: List<String>,
    @Json(name = "dishTypes")
    val dishTypes: List<String>,
    @Json(name = "extendedIngredients")
    val extendedIngredientDtos: List<ExtendedIngredientDto>,
    @Json(name = "gaps")
    val gaps: String,
    @Json(name = "glutenFree")
    val glutenFree: Boolean,
    @Json(name = "healthScore")
    val healthScore: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "imageType")
    val imageType: String,
    @Json(name = "license")
    val license: String?,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "lowFodmap")
    val lowFodmap: Boolean,
    @Json(name = "missedIngredientCount")
    val missedIngredientCount: Int,
    @Json(name = "occasions")
    val occasions: List<String>,
    @Json(name = "preparationMinutes")
    val preparationMinutes: Int,
    @Json(name = "pricePerServing")
    val pricePerServing: Double,
    @Json(name = "readyInMinutes")
    val readyInMinutes: Int,
    @Json(name = "servings")
    val servings: Int,
    @Json(name = "sourceName")
    val sourceName: String,
    @Json(name = "sourceUrl")
    val sourceUrl: String,
    @Json(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String,
    @Json(name = "summary")
    val summary: String,
    @Json(name = "sustainable")
    val sustainable: Boolean,
    @Json(name = "title")
    val title: String,
    @Json(name = "unusedIngredients")
    val unusedIngredients: List<Any>,
    @Json(name = "usedIngredientCount")
    val usedIngredientCount: Int,
    @Json(name = "usedIngredients")
    val usedIngredients: List<Any>,
    @Json(name = "vegan")
    val vegan: Boolean,
    @Json(name = "vegetarian")
    val vegetarian: Boolean,
    @Json(name = "veryHealthy")
    val veryHealthy: Boolean,
    @Json(name = "veryPopular")
    val veryPopular: Boolean,
    @Json(name = "weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int
)