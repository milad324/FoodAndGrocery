package com.shana.foodandgrocery.config

import com.shana.foodandgrocery.R

enum class MainScreen(val route: String, val title: String, val icon: Int) {
    MainMainScreen(route = "Home", title = "Home", icon = R.drawable.ic_home),
    SearchFilterMainScreen(route = "SearchFilter", title = "Search", icon = R.drawable.ic_search),
    FavoriteMainScreen(route = "FavoriteScreen", title = "Favorite", icon = R.drawable.ic_favorite),
    ShoppingMainScreen(route = "ShoppingScreen", title = "Shopping", icon = R.drawable.ic_add_shopping),
    Planner(route = "Planner", title = "Planner", icon = R.drawable.ic_planner)
}