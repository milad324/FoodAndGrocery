package com.shana.foodandgrocery.ui.screens


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shana.foodandgrocery.config.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var fullScreen by remember {
        mutableStateOf(false)
    }
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(Screen.MainScreen.route) {
            fullScreen = false
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
            fullScreen = false
            ShowRecipe()
        }
        composable(
            route = Screen.SearchFilterScreen.route
        ) {
            SearchScreen()
        }
    }
}