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
import androidx.paging.compose.collectAsLazyPagingItems
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.ui.components.recipe.FoodRecipeListView
import com.shana.foodandgrocery.viewModels.MainViewModel


@Composable
fun HomeScreen(
    onRecipeClick: (Recipe) -> Unit,
    onSearchFilterClick: () -> Unit,
) {
    Surface(modifier = Modifier.padding(8.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            FoodRecipeListView(onRecipeClick = onRecipeClick)
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = (onSearchFilterClick)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
    }

}




