package com.shana.foodandgrocery.ui.components.recipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.viewModels.MainViewModel

@Composable
fun FoodRecipeListView(
    onRecipeClick: (Recipe) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()

) {
    val recipes = mainViewModel.recipesFlow.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = recipes.loadState) {
        if (recipes.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (recipes.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()

        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (recipes.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    count = recipes.itemCount,
                    key = recipes.itemKey { it.recipeId },
                    contentType = recipes.itemContentType { "contentType" }
                ) { index ->
                    val item = recipes[index]
                    if (item != null)
                        FoodRecipeView(recipe = item, onRecipeClick)


                }
                item {
                    if (recipes.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }

        }

    }
}

