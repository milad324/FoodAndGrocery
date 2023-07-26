package com.shana.foodandgrocery.ui.components.recipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.viewModels.MainViewModel

@Composable
fun FoodRecipeListView( onRecipeClick: (Recipe) -> Unit,mainViewModel: MainViewModel = hiltViewModel()) {
   val  recipes = mainViewModel.recipesEntityFlow.collectAsLazyPagingItems()
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(recipes.itemCount) { index ->
                    if (recipes[index] != null) {
                        FoodRecipeView(recipe = recipes[index]!!,onRecipeClick)
                    }

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

