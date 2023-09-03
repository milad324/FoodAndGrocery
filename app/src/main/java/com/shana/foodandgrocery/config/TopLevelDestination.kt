package com.shana.foodandgrocery.config

import com.shana.foodandgrocery.R

enum class TopLevelDestination(val route: String, val title: String, val icon: Int) {
    MainTopLevelDestination(route = "Home", title = "Home", icon = R.drawable.ic_home),
    SearchFilterTopLevelDestination(route = "SearchFilter", title = "Search", icon = R.drawable.ic_search),
    FavoriteTopLevelDestination(route = "FavoriteScreen", title = "Favorite", icon = R.drawable.ic_favorite),
    ShoppingTopLevelDestination(route = "ShoppingScreen", title = "Shopping", icon = R.drawable.ic_add_shopping),
    Planner(route = "Planner", title = "Planner", icon = R.drawable.ic_planner)
}