package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    val aggregateLikes: Long,
    val cheap: Boolean,
    val dairyFree: Boolean,
    //val extendedIngredient: List<ExtendedIngredientEntity>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = true)
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