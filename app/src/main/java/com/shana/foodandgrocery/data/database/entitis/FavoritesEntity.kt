package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.util.Constants.Companion.FAVORITE_RECIPES_TABLE
import kotlinx.parcelize.RawValue

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(


    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    //val extendedIngredientEntity: @RawValue List<ExtendedIngredientEntity>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = false)
    val recipeId: Int,
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