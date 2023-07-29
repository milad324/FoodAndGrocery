package com.shana.foodandgrocery.data.mappers

import com.shana.foodandgrocery.data.database.entitis.ExtendedIngredientEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.network.dto.newDto.ExtendedIngredientDto
import com.shana.foodandgrocery.data.network.dto.newDto.RecipeDto
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

fun RecipeDto.toRecipeEntity(): RecipesEntity {
    return RecipesEntity(
        recipeId = id,
        image = image,
        veryHealthy = veryHealthy,
        vegetarian = vegetarian,
        vegan = vegan,
        summary = summary,
        sourceUrl = sourceUrl,
        sourceName = sourceName,
        readyInMinutes = readyInMinutes,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        cheap = cheap,
        title = title,
        aggregateLikes = aggregateLikes
    )
}

fun ExtendedIngredientDto.toExtendedIngredientEntity(): ExtendedIngredientEntity {
    return ExtendedIngredientEntity(
        id = 0,
        consistency = consistency,
        image = image,
        original = original,
        unit = unit,
        amount = amount,
        name = name,
        aisle = aisle,
        nameClean = nameClean,
        originalName = originalName,
        idEntity = id
    )
}


fun RecipesEntity.toRecipe(): Recipe {
    return Recipe(
        recipeId = recipeId,
        image = image,
        aggregateLikes = aggregateLikes,
        title = title,
        cheap = cheap,
        dairyFree = dairyFree,
        glutenFree = glutenFree,
        readyInMinutes = readyInMinutes,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        summary = summary,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        extendedIngredients = listOf()
    )
}


