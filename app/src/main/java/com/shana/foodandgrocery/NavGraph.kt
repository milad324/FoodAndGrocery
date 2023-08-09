package com.shana.foodandgrocery

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.ui.screens.favorite.FavoriteScreen
import com.shana.foodandgrocery.ui.screens.home.HomeScreen
import com.shana.foodandgrocery.ui.screens.planner.PlannerScreen
import com.shana.foodandgrocery.ui.screens.search.SearchScreen
import com.shana.foodandgrocery.ui.screens.shopping.ShoppingScreen
import com.shana.foodandgrocery.ui.screens.ShowRecipe


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
    ) {
        composable(Screen.MainScreen.route) {
            HomeScreen(onRecipeClick = {
                navController.navigate(Screen.ShowRecipe.withArgs(it.recipeId.toString()))
            }, onSearchFilterClick = {
                navController.navigate(Screen.SearchFilterScreen.route)
            })
        }
        composable(
            route = Screen.ShowRecipe.route + "/{recipeId}",
            arguments = listOf(
                navArgument(name = "recipeId") {
                    type = NavType.IntType
                    nullable = false
                }
            )) {
            ShowRecipe()
        }
        composable(
            route = Screen.SearchFilterScreen.route
        ) {
            SearchScreen()
        }
        composable(
            route = Screen.ShoppingScreen.route
        ) {
            ShoppingScreen()
        }
        composable(
            route = Screen.FavoriteScreen.route
        ) {
            FavoriteScreen()
        }
        composable(
            route = Screen.Planner.route
        ) {
            PlannerScreen()
        }
    }
}
