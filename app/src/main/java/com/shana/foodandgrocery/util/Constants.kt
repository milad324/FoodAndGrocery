package com.shana.foodandgrocery.util

class Constants {

    companion object {

        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

        // API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_OFFSET = "offset"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        // ROOM Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
        const val EXTENDED_INGREDIENT_TABLE = "extended_ingredient_table"
        const val RECIPES_EXTENDED_INGREDIENT_CROSS_REF_TABLE = "recipe_extended_ingredient_cross_ref_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"

        // Bottom Sheet and Preferences
        const val DEFAULT_RECIPES_NUMBER = 50
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"



        const val PREFERENCES_NAME = "food_preferences"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_QUERY_SEARCH = "query"
        const val PREFERENCES_DIET_TYPE = "dietType"


    }

}