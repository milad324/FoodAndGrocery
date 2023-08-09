package com.shana.foodandgrocery.ui.screens.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.favorite.FavoriteScreen

fun NavController.navigateFavorite(navOptions: NavOptions? = null) {
    this.navigate(Screen.FavoriteScreen.route, navOptions)
}
fun NavGraphBuilder.favoriteScreen() {
    composable(
        route = Screen.FavoriteScreen.route
    ) {
        FavoriteScreen()
    }
}