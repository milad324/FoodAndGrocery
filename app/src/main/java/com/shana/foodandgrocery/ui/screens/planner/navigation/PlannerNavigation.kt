package com.shana.foodandgrocery.ui.screens.planner.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.home.HomeScreen
import com.shana.foodandgrocery.ui.screens.planner.PlannerScreen

fun NavController.navigatePlanner(navOptions: NavOptions? = null) {
    this.navigate(Screen.Planner.route, navOptions)
}
fun NavGraphBuilder.plannerScreen() {
    composable(
        route = Screen.Planner.route
    ) {
        PlannerScreen()
    }
}