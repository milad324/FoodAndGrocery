package com.shana.foodandgrocery

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.favorite.navigation.favoriteScreen
import com.shana.foodandgrocery.ui.screens.home.navigation.homeScreen
import com.shana.foodandgrocery.ui.screens.planner.navigation.plannerScreen
import com.shana.foodandgrocery.ui.screens.search.navigation.searchScreen
import com.shana.foodandgrocery.ui.screens.shopping.navigation.shoppingScreen
import com.shana.foodandgrocery.ui.screens.showRecipe.navigation.navigateShowRecipe
import com.shana.foodandgrocery.ui.screens.showRecipe.navigation.showRecipeScreen


@Composable
fun NavGraph(
    appState: FoodAndGroceryState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = appState.startDestination,
    ) {
        homeScreen(onRecipeClick = {
            navController.navigateShowRecipe(it)
        }, nestedGraphs = {
            showRecipeScreen()
        })
        searchScreen()
        favoriteScreen(onRecipeClick = {
            navController.navigateShowRecipe(it)
        })
        shoppingScreen()
        plannerScreen()
    }
}
