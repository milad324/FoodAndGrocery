package com.shana.foodandgrocery.ui.screens.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.home.HomeScreen
import com.shana.foodandgrocery.ui.screens.search.SearchScreen

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    this.navigate(Screen.SearchFilterScreen.route, navOptions)
}
fun NavGraphBuilder.searchScreen() {
    composable(
        route = Screen.SearchFilterScreen.route
    ) {
     SearchScreen()
    }
}