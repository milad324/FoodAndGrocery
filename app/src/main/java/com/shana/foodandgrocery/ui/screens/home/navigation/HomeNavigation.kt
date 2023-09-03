package com.shana.foodandgrocery.ui.screens.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shana.foodandgrocery.config.TopLevelDestination
import com.shana.foodandgrocery.ui.screens.home.HomeScreen
import com.shana.foodandgrocery.util.Constants.Companion.START_DESTINATION


fun NavController.navigateHome(navOptions: NavOptions? = null) {
    this.navigate(START_DESTINATION, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onRecipeClick: (Long) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    onSearchFilterClick: () -> Unit
) {
    navigation(
        route = START_DESTINATION,
        startDestination = TopLevelDestination.MainTopLevelDestination.route
    ) {
        composable(
            route = TopLevelDestination.MainTopLevelDestination.route
        ) {
            HomeScreen(onRecipeClick = onRecipeClick, onSearchFilterClick = onSearchFilterClick)
        }
        nestedGraphs()
    }

}