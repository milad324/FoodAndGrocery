package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.util.Constants.Companion.FAVORITE_RECIPES_TABLE
import kotlinx.parcelize.RawValue

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoriteRecipeEntity(
    val aggregateLikes: Long,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredientsEntity: @RawValue List<ExtendedIngredient>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = false)
    val recipeId: Long,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,

    )