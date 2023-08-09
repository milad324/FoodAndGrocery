package com.shana.foodandgrocery.ui.screens.shopping.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.home.HomeScreen
import com.shana.foodandgrocery.ui.screens.shopping.ShoppingScreen

fun NavController.navigateShopping(navOptions: NavOptions? = null) {
    this.navigate(Screen.ShoppingScreen.route, navOptions)
}

fun NavGraphBuilder.shoppingScreen() {
    composable(
        route = Screen.ShoppingScreen.route
    ) {
        ShoppingScreen()
    }
}