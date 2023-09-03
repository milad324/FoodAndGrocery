package com.shana.foodandgrocery.util

import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.models.Recipe

class FakeValue {
    companion object{
        fun fakeRecipe():Recipe{
            return  Recipe(
                recipeId = 10,
                title = "Title",
                image = "",
                aggregateLikes = 10,
                cheap = false,
                dairyFree = true,
                glutenFree = false,
                readyInMinutes = 20,
                sourceName = "source name",
                sourceUrl = "source url",
                summary = "sumery",
                vegan = true,
                vegetarian = true,
                veryHealthy = true,
                extendedIngredients = listOf(
                    ExtendedIngredient(
                        image = "lemon-juice.jpg",
                        aisle = "",
                        unit = "gr",
                        id = 10,
                        original = "original",
                        amount = 120.0,
                        name = "name",
                        consistency = "consistency"
                    ), ExtendedIngredient(
                        image = "lemon-juice.jpg",
                        aisle = "",
                        unit = "gr",
                        id = 10,
                        original = "original",
                        amount = 120.0,
                        name = "name",
                        consistency = "consistency"
                    ), ExtendedIngredient(
                        image = "lemon-juice.jpg",
                        aisle = "",
                        unit = "gr",
                        id = 10,
                        original = "original",
                        amount = 120.0,
                        name = "name",
                        consistency = "consistency"
                    ), ExtendedIngredient(
                        image = "lemon-juice.jpg",
                        aisle = "",
                        unit = "gr",
                        id = 10,
                        original = "original",
                        amount = 120.0,
                        name = "name",
                        consistency = "consistency"
                    )
                ))
        }
    }

}