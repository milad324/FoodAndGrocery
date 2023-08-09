package com.shana.foodandgrocery.ui.screens.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.home.HomeScreen

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    this.navigate(Screen.MainScreen.route, navOptions)
}
fun NavGraphBuilder.homeScreen() {
    composable(
        route = Screen.MainScreen.route
    ) {
        HomeScreen(onRecipeClick = {}) {

        }
    }
}