package com.shana.foodandgrocery.ui.screens.shopping.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.MainScreen
import com.shana.foodandgrocery.ui.screens.shopping.ShoppingScreen

fun NavController.navigateShopping(navOptions: NavOptions? = null) {
    this.navigate(MainScreen.ShoppingMainScreen.route, navOptions)
}

fun NavGraphBuilder.shoppingScreen() {
    composable(
        route = MainScreen.ShoppingMainScreen.route
    ) {
        ShoppingScreen()
    }
}