package com.shana.foodandgrocery.ui.screens.favorite

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shana.foodandgrocery.ui.components.recipe.FoodRecipeView

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onRecipeClick: (Long) -> Unit
) {
    val favoriteRecipeList = favoriteViewModel.favoriteRecipeList.observeAsState()
    favoriteRecipeList.value?.let {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
        ) {
            items(
                count = it.size,
            ) { index ->
                val recipe = it[index]
                FoodRecipeView(
                    recipe = recipe,
                    onRecipeClick = { onRecipeClick(recipe.recipeId) },
                    true
                )
            }
        }
    }
}