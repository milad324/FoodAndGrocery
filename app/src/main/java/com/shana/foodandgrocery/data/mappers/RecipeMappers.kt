package com.shana.foodandgrocery.data.mappers

import com.shana.foodandgrocery.data.database.entitis.ExtendedIngredientEntity
import com.shana.foodandgrocery.data.database.entitis.FavoriteRecipeEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
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
                consistency = extendedIngredientItem.consistency,
                aisle = extendedIngredientItem.aisle,
                id = extendedIngredientItem.id
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
fun Recipe.toFavoriteRecipeEntity(): FavoriteRecipeEntity {
    return FavoriteRecipeEntity(
        aggregateLikes = aggregateLikes,
        cheap = cheap,
        dairyFree = dairyFree,
        glutenFree = glutenFree,
        title = title,
        recipeId = recipeId,
        image = image,
        extendedIngredientsEntity = extendedIngredients.map { it ->
            ExtendedIngredient(
                image = it.image,
                unit = it.unit,
                original = it.original,
                consistency = it.consistency,
                amount = it.amount,
                name = it.name,
                id = it.id,
                aisle = it.aisle
            )
        },
        readyInMinutes = readyInMinutes,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        summary = summary,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy
    )
}
fun FavoriteRecipeEntity.toRecipe(): Recipe {
    return Recipe(aggregateLikes = aggregateLikes,
        recipeId = recipeId,
        image = image,
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
        extendedIngredients = extendedIngredientsEntity.map { extendedIngredientItem ->
            ExtendedIngredient(
                image = extendedIngredientItem.image,
                name = extendedIngredientItem.name,
                amount = extendedIngredientItem.amount,
                unit = extendedIngredientItem.unit,
                original = extendedIngredientItem.original,
                consistency = extendedIngredientItem.consistency,
                aisle = extendedIngredientItem.aisle,
                id = extendedIngredientItem.id
            )
        })
}
fun ExtendedIngredient.toShoppingList(recipe: Recipe): ShoppingItemEntity {
    return ShoppingItemEntity(
        recipeId = recipe.recipeId,
        recipeName = recipe.title,
        name = name,
        amount = amount,
        consistency = consistency,
        image = image,
        ingredientId = id,
        aisle = aisle,
        nameClean = name,
        original = original,
        originalName = original,
        unit = unit
    )
}
