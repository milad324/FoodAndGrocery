package com.shana.foodandgrocery.ui.screens.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.MainScreen
import com.shana.foodandgrocery.ui.screens.favorite.FavoriteScreen

fun NavController.navigateFavorite(navOptions: NavOptions? = null) {
    this.navigate(MainScreen.FavoriteMainScreen.route, navOptions)
}
fun NavGraphBuilder.favoriteScreen(onRecipeClick: (Long) -> Unit) {
    composable(
        route = MainScreen.FavoriteMainScreen.route
    ) {
        FavoriteScreen(onRecipeClick = onRecipeClick)
    }
}