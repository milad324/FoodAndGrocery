package com.shana.foodandgrocery.ui.screens.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.TopLevelDestination
import com.shana.foodandgrocery.ui.screens.search.SearchScreen

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    this.navigate(TopLevelDestination.SearchFilterTopLevelDestination.route, navOptions)
}
fun NavGraphBuilder.searchScreen() {
    composable(
        route = TopLevelDestination.SearchFilterTopLevelDestination.route
    ) {
     SearchScreen()
    }
}