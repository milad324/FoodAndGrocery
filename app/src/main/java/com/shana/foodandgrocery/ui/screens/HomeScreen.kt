package com.shana.foodandgrocery.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.ui.components.recipe.FoodRecipeListView
import com.shana.foodandgrocery.viewModels.MainViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    Surface(modifier = Modifier.padding(8.dp)) {
        val foodRecipe = mainViewModel.recipesEntityFlow.collectAsLazyPagingItems()
        FoodRecipeListView(foodRecipe,navController)
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = {/*TODO:navigate to search*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
    }

}




