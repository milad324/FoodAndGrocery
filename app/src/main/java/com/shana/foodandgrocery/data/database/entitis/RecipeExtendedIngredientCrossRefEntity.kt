package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import com.shana.foodandgrocery.util.Constants.Companion.RECIPES_EXTENDED_INGREDIENT_CROSS_REF_TABLE


@Entity(tableName = RECIPES_EXTENDED_INGREDIENT_CROSS_REF_TABLE, primaryKeys = ["recipeId", "id"])
data class RecipeExtendedIngredientCrossRefEntity(
    val recipeId: Int,
    val id: Int,
)