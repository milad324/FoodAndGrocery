package com.shana.foodandgrocery.config

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.vector.ImageVector
import com.shana.foodandgrocery.R

enum class Screen(val route: String, val title: String, val icon: Int) {
    MainScreen(route = "Home", title = "Home", icon = R.drawable.ic_home),
    //ShowRecipe(route = "SearchRecipe", title = "Search", icon = R.drawable.ic_search),
    SearchFilterScreen(route = "SearchFilter", title = "Search", icon = R.drawable.ic_search),
    FavoriteScreen(route = "FavoriteScreen", title = "Favorite", icon = R.drawable.ic_favorite),
    ShoppingScreen(route = "ShoppingScreen", title = "Shopping", icon = R.drawable.ic_add_shopping),
    Planner(route = "Planner", title = "Planner", icon = R.drawable.ic_planner)
}