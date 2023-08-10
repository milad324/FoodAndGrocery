package com.shana.foodandgrocery.ui.screens.showRecipe.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shana.foodandgrocery.ui.screens.showRecipe.ShowRecipe
import com.shana.foodandgrocery.util.Constants.Companion.RECIPE_ID_SAVED_STATE_KEY

fun NavController.navigateShowRecipe(recipeId: Long) {
    this.navigate("showRecipe/$recipeId") {
        launchSingleTop = true
    }
}
fun NavGraphBuilder.showRecipeScreen() {
    composable(
        route = "showRecipe/{$RECIPE_ID_SAVED_STATE_KEY}",
        arguments = listOf(
            navArgument(name = RECIPE_ID_SAVED_STATE_KEY) {
                type = NavType.LongType
                nullable = false
            }
        )
    ) {
        ShowRecipe()
    }
}