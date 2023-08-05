package com.shana.foodandgrocery.config

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.vector.ImageVector
import com.shana.foodandgrocery.R

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object MainScreen : Screen(route = "Home", title = "Home", icon = R.drawable.ic_home)
    object ShowRecipe :
        Screen(route = "SearchRecipe", title = "Search", icon = R.drawable.ic_search)

    object SearchFilterScreen :
        Screen(route = "SearchFilter", title = "Search Filter", icon = R.drawable.ic_search)

    object FavoriteScreen :
        Screen(route = "FavoriteScreen", title = "Favorite", icon = R.drawable.ic_favorite)

    object ShoppingScreen :
        Screen(route = "ShoppingScreen", title = "Shopping List", icon = R.drawable.ic_add_shopping)

    object Planner : Screen(route = "Planner", title = "Planner", icon = R.drawable.ic_planner)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}