package com.shana.foodandgrocery.data.mappers

import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.models.Recipe

fun RecipeWithExtendedIngredients.toRecipe(): Recipe {
    return Recipe(aggregateLikes = recipesEntity.aggregateLikes,
        recipeId = recipesEntity.recipeId,
        image = recipesEntity.image,
        title = recipesEntity.title,
        cheap = recipesEntity.cheap,
        dairyFree = recipesEntity.dairyFree,
        glutenFree = recipesEntity.glutenFree,
        readyInMinutes = recipesEntity.readyInMinutes,
        sourceName = recipesEntity.sourceName,
        sourceUrl = recipesEntity.sourceUrl,
        summary = recipesEntity.summary,
        vegan = recipesEntity.vegan,
        vegetarian = recipesEntity.vegetarian,
        veryHealthy = recipesEntity.veryHealthy,
        extendedIngredients = extendedIngredient.map { extendedIngredientItem ->
            ExtendedIngredient(
                image = extendedIngredientItem.image,
                name = extendedIngredientItem.name,
                amount = extendedIngredientItem.amount,
                unit = extendedIngredientItem.unit,
                original = extendedIngredientItem.original,
                consistency = extendedIngredientItem.consistency
            )
        })
}




