package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredient: List<ExtendedIngredientEntity>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = true)
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
{
    fun toRecipeModel():Recipe{
        return Recipe( aggregateLikes, cheap, dairyFree, extendedIngredient.map { item->
            ExtendedIngredient(amount = item.amount, image = item.image, name = item.name, unit = item.unit, original = item.original, consistency = item.consistency)

        }, glutenFree, recipeId, image, readyInMinutes, sourceName, sourceUrl, summary, title, vegan, vegetarian, veryHealthy)
    }
}